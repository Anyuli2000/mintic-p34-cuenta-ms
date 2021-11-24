package com.misiontic.futbolinms.controllers;

import com.misiontic.futbolinms.exceptions.AccountNotFoundException;
import com.misiontic.futbolinms.exceptions.InsufficientBalanceException;
import com.misiontic.futbolinms.models.Account;
import com.misiontic.futbolinms.models.Transaction;
import com.misiontic.futbolinms.models.Sala;
import com.misiontic.futbolinms.repositories.AccountRepository;
import com.misiontic.futbolinms.repositories.SalaRepository;
import com.misiontic.futbolinms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TransactionController {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final SalaRepository salaRepository;

    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository, SalaRepository salaRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.salaRepository = salaRepository;
    }

    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction){
        Account cuentaOrigen =
                accountRepository.findById(transaction.getCuentaOrigen()).orElse(null);
        Sala cuentaDestino=
                salaRepository.findById(transaction.getCuentaDestino()).orElse(null);

        if (cuentaOrigen == null)
            throw new AccountNotFoundException("No se encontro la cuenta: " + transaction.getCuentaOrigen()+" para poder realizar la apuesta");

        if (cuentaDestino == null)
            throw new AccountNotFoundException("No se encontro la cuenta: " + transaction.getCuentaDestino()+" para poder realizar la apuesta");

        if (cuentaOrigen.getBalance()< transaction.getDinero())
            throw new InsufficientBalanceException("no tiene suficiente dinero para cumplir con la cuota de apuesta que digito ");

        if (transaction.getEquipo().toString().equals("A")) {

            String origencuenta = (transaction.getCuentaOrigen());
            cuentaDestino.getEquipoA().add(origencuenta);
            cuentaDestino.setCuenta(cuentaDestino.getCuenta() + transaction.getDinero());

            salaRepository.save(cuentaDestino);




        }

        if (transaction.getEquipo().toString().equals("B") ) {

            String origencuenta2 = (transaction.getCuentaOrigen());
            cuentaDestino.getEquipoB().add(origencuenta2);
            cuentaDestino.setCuenta(cuentaDestino.getCuenta() + transaction.getDinero());
            salaRepository.save(cuentaDestino);
        }

        cuentaOrigen.setBalance(cuentaOrigen.getBalance() - transaction.getDinero());
        cuentaOrigen.setLastChange(new Date());
        cuentaOrigen.setUltimaApuesta(transaction.getDinero());
        accountRepository.save(cuentaOrigen);

        transaction.setDate(new Date());
        return transactionRepository.save(transaction);
    }

    @PostMapping ("/transactions/sala/{id}")
    void getSala(@PathVariable String id){

        Sala transaccionSala= salaRepository.findById(id).orElse(null);

        String tituloSala = transaccionSala.getTitulo();
        int jugadoresA =  transaccionSala.getEquipoA().size ();
        int jugadoresB = transaccionSala.getEquipoB().size();

        if (transaccionSala.getMarcador1()> transaccionSala.getMarcador2()){

            for (String cadaCuenta:transaccionSala.getEquipoA()) {

                Account cuentaJugador = accountRepository.findById(cadaCuenta).orElse(null);
                int apuestas = cuentaJugador.getApuestasGanadas()+1;

                if (cuentaJugador== null)
                    throw new AccountNotFoundException("No se encontro la cuenta: " + cadaCuenta +" para poder realizar la apuesta");
                Integer inver = cuentaJugador.getUltimaApuesta();
                Integer dinero= transaccionSala.getCuenta() / jugadoresA;
                cuentaJugador.setBalance(cuentaJugador.getBalance()+dinero);
                cuentaJugador.getApuestasExitosas().add(tituloSala);
                cuentaJugador.setInversion(cuentaJugador.getInversion()+inver);
                cuentaJugador.setActivo(cuentaJugador.getActivo()+dinero);
                cuentaJugador.setApuestasGanadas(apuestas);
                cuentaJugador.setActivoNeto(cuentaJugador.getActivo()-cuentaJugador.getPasivo());

                accountRepository.save(cuentaJugador);
                System.out.println(apuestas);

                if (apuestas <= 10 ){
                    String nivelcito = "Razo";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
                if (apuestas > 10 & apuestas <= 20){
                    String nivelcito = "Profesional";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
                if (apuestas > 20 & apuestas <= 40){
                    String nivelcito = "Maestro";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
                if (apuestas > 40 ){
                    String nivelcito = "Gran Maestro";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }

            }

            for (String cadaCuenta:transaccionSala.getEquipoB()){
                Account cuentaJugador = accountRepository.findById(cadaCuenta).orElse(null);


                if (cuentaJugador== null)
                    throw new AccountNotFoundException("No se encontro la cuenta: " + cadaCuenta );
                Integer inver = cuentaJugador.getUltimaApuesta();
                cuentaJugador.getApuestasPerdidas().add(tituloSala);
                cuentaJugador.setInversion(cuentaJugador.getInversion()+inver);
                cuentaJugador.setPasivo(cuentaJugador.getPasivo()+inver);
                accountRepository.save(cuentaJugador);
            }

            System.out.println("entro al IF del equipo A");

        }

        if (transaccionSala.getMarcador1()< transaccionSala.getMarcador2()){

            for (String cadaCuenta:transaccionSala.getEquipoB()) {

                Account cuentaJugador = accountRepository.findById(cadaCuenta).orElse(null);

                int apuestas = cuentaJugador.getApuestasGanadas()+1;
                if (cuentaJugador== null)
                    throw new AccountNotFoundException("No se encontro la cuenta: " + cadaCuenta +" para poder realizar la apuesta");

                Integer inver = cuentaJugador.getUltimaApuesta();
                Integer dinero= transaccionSala.getCuenta() / jugadoresB;
                cuentaJugador.setBalance(cuentaJugador.getBalance()+dinero);
                cuentaJugador.getApuestasExitosas().add(tituloSala);
                cuentaJugador.setInversion(cuentaJugador.getInversion()+inver);
                cuentaJugador.setActivo(cuentaJugador.getActivo()+dinero);
                cuentaJugador.setApuestasGanadas(apuestas);
                cuentaJugador.setActivoNeto(cuentaJugador.getActivo()-cuentaJugador.getPasivo());

                accountRepository.save(cuentaJugador);
                System.out.println(apuestas);

                if (apuestas <= 10 ){
                    String nivelcito = "Razo";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
                if (apuestas > 10 & apuestas <= 20){
                    String nivelcito = "Profesional";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
                if (apuestas > 20 & apuestas <= 40){
                    String nivelcito = "Maestro";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
                if (apuestas > 40 ){
                    String nivelcito = "Gran Maestro";
                    cuentaJugador.setNivel(nivelcito);
                    accountRepository.save(cuentaJugador);
                }
            }

            for (String cadaCuenta:transaccionSala.getEquipoA()){
                Account cuentaJugador = accountRepository.findById(cadaCuenta).orElse(null);

                if (cuentaJugador== null)
                    throw new AccountNotFoundException("No se encontro la cuenta: " + cadaCuenta );
                Integer inver = cuentaJugador.getUltimaApuesta();

                cuentaJugador.getApuestasPerdidas().add(tituloSala);
                cuentaJugador.setInversion(cuentaJugador.getInversion()+inver);
                cuentaJugador.setPasivo(cuentaJugador.getPasivo()+inver);

                accountRepository.save(cuentaJugador);
            }
            System.out.println("entro al IF del equipoB");

        }

        if (transaccionSala.getMarcador1().equals(transaccionSala.getMarcador2())){
            for (String cadaCuenta:transaccionSala.getEquipoA()){
                Account cuentaJugador = accountRepository.findById(cadaCuenta).orElse(null);
                cuentaJugador.setBalance(cuentaJugador.getBalance()+cuentaJugador.getUltimaApuesta());
                accountRepository.save(cuentaJugador);
            }
            for (String cadaCuenta:transaccionSala.getEquipoB()){
                Account cuentaJugador = accountRepository.findById(cadaCuenta).orElse(null);
                cuentaJugador.setBalance(cuentaJugador.getBalance()+cuentaJugador.getUltimaApuesta());
                accountRepository.save(cuentaJugador);
            }
            System.out.println("Entro al if de igualdad de equipos");
        }



        return ;

    }

    @GetMapping("/transactions/{username}")
    List<Transaction> userTransaction(@PathVariable String username) {
        Account userAccount = accountRepository.findById(username).orElse(null);
        if (userAccount == null)
            throw new AccountNotFoundException("No se encontro una cuenta con el username: " + username);
        List<Transaction> transactionsOrigin =
                transactionRepository.findByCuentaOrigen(username);
        return transactionsOrigin;
    }


}
