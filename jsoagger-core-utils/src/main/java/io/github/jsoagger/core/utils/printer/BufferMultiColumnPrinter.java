/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */


package io.github.jsoagger.core.utils.printer;

/**
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public class BufferMultiColumnPrinter extends MultiColumnPrinter {

  private StringBuilder builder = new StringBuilder();


  /**
   * @param numCol
   * @param gap
   */
  public BufferMultiColumnPrinter(int numCol) {
    super(numCol, 10, "-");
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void doPrint(String str) {
    builder.append(str);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void doPrintln(String str) {
    builder.append(str);
    builder.append("\n");
  }


  /**
   * @return the builder
   */
  public StringBuilder getBuilder() {
    return builder;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return builder.toString();
  }
}
