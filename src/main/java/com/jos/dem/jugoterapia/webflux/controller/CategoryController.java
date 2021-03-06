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

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;

import com.jos.dem.jugoterapia.webflux.model.Category;
import com.jos.dem.jugoterapia.webflux.model.Beverage;
import com.jos.dem.jugoterapia.webflux.util.LanguageResolver;
import com.jos.dem.jugoterapia.webflux.service.CategoryService;
import com.jos.dem.jugoterapia.webflux.service.BeverageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(tags={"knows how receive manage category requests"})
@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
  @Autowired
  private BeverageService beverageService;
  @Autowired
  private LanguageResolver languageResolver;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/")
  public Flux<Category> getCategories(){
    log.info("Listing categories");
    return categoryService.findByI18n("es");
  }

  @ApiImplicitParam(name = "language", value = "Language required", required = true, dataType = "string", paramType = "path")
  @GetMapping("/{language}")
  public Flux<Category> getCategories(@PathVariable("language") String language){
    log.info("Listing categories");
    return categoryService.findByI18n(languageResolver.resolve(language));
  }

  @ApiImplicitParam(name = "id", value = "Category's id", required = true, dataType = "int", paramType = "path")
  @GetMapping(value="/{id}/beverages")
  public Flux<Beverage> getBeverages(@PathVariable("id") Integer categoryId){
    log.info("Listing beverages by category: {}", categoryId);
    return beverageService.findByCategoryId(categoryId);
  }

}
