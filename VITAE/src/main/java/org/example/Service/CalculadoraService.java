package org.example.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CalculadoraService {

    public double dividir(double a, double b){

        if (b == 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Divisão por 0 não existe"
            );
        }

        return a / b;

    }

}
