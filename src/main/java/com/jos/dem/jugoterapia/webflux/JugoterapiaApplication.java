/*
  Copyright 2018 José Luis De la Cruz Morales <joseluis.delacruz@gmail.com>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.jos.dem.jugoterapia.webflux;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class JugoterapiaApplication {

  @Value("${swagger.host}")
  private String host;

	public static void main(String[] args) {
    SpringApplication.run(JugoterapiaApplication.class, args);
	}

  @Bean
  public WebClient webClient() {
    return WebClient
      .builder()
        .baseUrl(host)
        .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
      .build();
  }

}
