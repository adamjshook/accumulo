/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.accumulo.test.randomwalk.conditional;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.TreeSet;

import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.test.randomwalk.Environment;
import org.apache.accumulo.test.randomwalk.State;
import org.apache.accumulo.test.randomwalk.Test;
import org.apache.hadoop.io.Text;

/**
 *
 */
public class Split extends Test {
  @Override
  public void visit(State state, Environment env, Properties props) throws Exception {
    String table = state.getString("tableName");
    Random rand = (Random) state.get("rand");
    Connector conn = env.getConnector();
    String row = Utils.getBank(rand.nextInt((Integer) state.get("numBanks")));

    log.debug("adding split " + row);
    conn.tableOperations().addSplits(table, new TreeSet<>(Arrays.asList(new Text(row))));
  }

}
