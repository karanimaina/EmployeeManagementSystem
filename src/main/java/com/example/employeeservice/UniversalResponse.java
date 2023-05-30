package com.example.employeeservice;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UniversalResponse (int  status, Object data, String message){
  public static UniversalResponseBuilder builder(){
      return  new UniversalResponseBuilder();
  }


}
