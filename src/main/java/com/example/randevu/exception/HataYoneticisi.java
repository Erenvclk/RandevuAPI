package com.example.randevu.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.randevu.dto.HataResponse;

@RestControllerAdvice // bu sınıf tüm controllera bakar. controllerdan gelen hatada once buraya bakar.

public class HataYoneticisi {
    @ExceptionHandler(MethodArgumentNotValidException.class) // @exceptionhandler(X.class) -> X hatası fırlayınca bu
                                                             // method çalıssın.
                                                             // methodargumentnotvaliderror -> @valid hatasu
    @ResponseStatus(HttpStatus.BAD_REQUEST) // hata kodu 400 olsun
    public HataResponse gecersizVeri(MethodArgumentNotValidException e) {
        String mesaj = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(hata -> hata.getDefaultMessage())
        .collect(Collectors.joining(", "));
        return new HataResponse(400, mesaj);
    }

    @ExceptionHandler(KayitBulunamadiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HataResponse kayitBulunamadi(KayitBulunamadiException e) {
        String mesaj = e.getMessage();
        return new HataResponse(404, mesaj);
    }

    @ExceptionHandler(CakismaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public HataResponse CakismaHatasi(CakismaException e) {
        String mesaj = e.getMessage();
        return new HataResponse(409, mesaj);
    }

    @ExceptionHandler(GirisHatasiException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) 
    public HataResponse GirisHatasi(GirisHatasiException e){
        String mesaj = e.getMessage();
        return  new HataResponse(401 , mesaj);
    }

}
