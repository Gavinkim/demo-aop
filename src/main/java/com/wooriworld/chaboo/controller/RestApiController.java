package com.wooriworld.chaboo.controller;

import com.wooriworld.chaboo.annotation.Decode;
import com.wooriworld.chaboo.annotation.Timer;
import com.wooriworld.chaboo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

  @GetMapping("/get/{id}")
  public String get(@PathVariable Long id, @RequestParam String name){
    return String.format("%s-%s",id, name);
  }

  @PostMapping("/post")
  public UserDto post(@RequestBody UserDto userDto){
    return userDto;
  }

  @Decode
  @PutMapping("/put")
  public UserDto put(@RequestBody UserDto userDto){
    log.info("put: {}",userDto);
    return userDto;
  }

  @Timer
  @DeleteMapping("/delete")
  public void delete() throws InterruptedException {
    Thread.sleep(2000);
  }
}
