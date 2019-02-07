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

package com.jos.dem.jugoterapia.webflux.controller;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;

import com.jos.dem.jugoterapia.webflux.model.Beverage;
import com.jos.dem.jugoterapia.webflux.service.BeverageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(tags = {"knows how receive manage beverage requests"})
@RestController
@RequestMapping("/beverages")
public class BeverageController {

  @Autowired
  private BeverageService beverageService;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @ApiImplicitParam(name = "id", value = "Beverage's id", required = true, dataType = "int", paramType = "path")
  @GetMapping("/{id}")
  public Mono<Beverage> getBeverage(@PathVariable("id") Integer beverageId){
    log.info("Listing beverages by id: {}", beverageId);
    return beverageService.findById(beverageId);
  }

  @ApiImplicitParam(name = "keyword", value = "Beverage ingredients contain keyword", required = true, dataType = "string", paramType = "path")
  @GetMapping("/ingredients/{keyword}")
  public Mono<Beverage> getBeverageByKeyword(@PathVariable("keyword") String keyword){
    log.info("Listing beverages by keyword: {}", keyword);
    return beverageService.findByIngredientKeyword(keyword);
  }

}
