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

package com.jos.dem.jugoterapia.webflux.config;

import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import com.jos.dem.jugoterapia.webflux.model.Category;
import com.jos.dem.jugoterapia.webflux.model.Beverage;
import com.jos.dem.jugoterapia.webflux.repository.CategoryRepository;
import com.jos.dem.jugoterapia.webflux.repository.BeverageRepository;

@Component
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private BeverageRepository beverageRepository;

  private List<Beverage> beverages = Arrays.asList(
      new Beverage(1, "Jugo para evitar los calambres", "3 tallos de apio \n 1/4 de pepino", "Mezcla 3 tallos de apio y 1/4 de pepino y procésalos en el extractor de jugos. Si lo deseas puedes debajarlo con agua", 1));

  private List<Category> categories = Arrays.asList(
      new Category(1, "Curativos"),
      new Category(2, "Energizantes"),
      new Category(3, "Saludables"),
      new Category(4, "Estimulantes"));

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    System.out.println("Loading data...");
    validateCategories();
    validateBeverages();
  }

  private void validateCategories(){
    categoryRepository.findById(1)
        .subscribe(
            curativos -> System.out.println("Curativos: " + curativos),
            error -> error.printStackTrace(),
            () -> categories.forEach(category -> categoryRepository.save(category).subscribe())
        );
  }

  private void validateBeverages(){
    beverageRepository.findById(1)
        .subscribe(
            juice -> System.out.println("Beverage: " + juice),
            error -> error.printStackTrace(),
            () -> beverages.forEach(beverage -> beverageRepository.save(beverage).subscribe())
        );
  }

}

