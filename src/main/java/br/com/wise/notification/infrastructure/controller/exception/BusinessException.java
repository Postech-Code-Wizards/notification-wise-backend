package br.com.wise.notification.infrastructure.controller.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

   public BusinessException(String messageCode){
       super(messageCode);
   }

}