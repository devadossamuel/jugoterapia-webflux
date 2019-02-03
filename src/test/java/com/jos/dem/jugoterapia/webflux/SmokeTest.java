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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.jos.dem.jugoterapia.webflux.controller.SanityController;
import com.jos.dem.jugoterapia.webflux.controller.CategoryController;
import com.jos.dem.jugoterapia.webflux.controller.BeverageController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

  @Autowired
  private SanityController sanityController;
  @Autowired
  private CategoryController categoryController;
  @Autowired
  private BeverageController beverageController;

  @Test
  public void shouldLoadContext() throws Exception {
    assertThat(sanityController).isNotNull();
    assertThat(categoryController).isNotNull();
    assertThat(beverageController).isNotNull();
  }

}
