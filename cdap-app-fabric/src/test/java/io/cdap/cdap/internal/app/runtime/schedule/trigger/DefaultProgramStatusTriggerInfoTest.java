/*
 * Copyright © 2021 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.cdap.internal.app.runtime.schedule.trigger;

import io.cdap.cdap.AllProgramsApp;
import io.cdap.cdap.api.ProgramStatus;
import io.cdap.cdap.api.app.ProgramType;
import io.cdap.cdap.common.app.RunIds;
import org.apache.twill.api.RunId;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Collections;

public class DefaultProgramStatusTriggerInfoTest {

  @Test
  public void testSerializationAndDeserialization() throws IOException, ClassNotFoundException {
    String namespace = "testSerializationAndDeserializationNamespace";

    DefaultProgramStatusTriggerInfo triggerInfo =
      new DefaultProgramStatusTriggerInfo("testNamespace", AllProgramsApp.NAME,
                                          ProgramType.WORKFLOW,
                                          AllProgramsApp.class.getSimpleName(),
                                          RunIds.generate(), ProgramStatus.COMPLETED, null,
                                          Collections.emptyMap());
    String seralized = serializeToStringBase64(triggerInfo);

    DefaultProgramStatusTriggerInfo deserializedTriggerInfo =
      (DefaultProgramStatusTriggerInfo) deSerializeFromStringBase64(seralized);
    Assert.assertEquals(triggerInfo.getNamespace(), deserializedTriggerInfo.getNamespace());
    Assert.assertEquals(triggerInfo.getApplicationName(), deserializedTriggerInfo.getApplicationName());
    Assert.assertEquals(triggerInfo.getProgramType(), deserializedTriggerInfo.getProgramType());
    Assert.assertEquals(triggerInfo.getProgram(), deserializedTriggerInfo.getProgram());
    Assert.assertEquals(triggerInfo.getRunId(), deserializedTriggerInfo.getRunId());
  }

  @Test
  public void testDeserializationVersioning() throws IOException, ClassNotFoundException {
    String namespace = "testDeserializationVersioningNamespace";
    RunId runId = RunIds.fromString("78fe52c1-3921-11ec-8cc1-000000779c42");

    // "serialized" is a serialized DefaultProgramStatusTriggerInfo object when
    // serialVersionUID is 1. Generated by the code below.
    //
    // DefaultProgramStatusTriggerInfo triggerInfo =
    //  new DefaultProgramStatusTriggerInfo(namespace, AllProgramsApp.NAME,
    //                                      ProgramType.WORKFLOW,
    //                                      AllProgramsApp.class.getSimpleName(),
    //                                      runId, ProgramStatus.COMPLETED, null,
    //                                      Collections.emptyMap());
    // String serialized = serializeToStringBase64(triggerInfo);
    String serialized = "rO0ABXNyAFJpby5jZGFwLmNkYXAuaW50ZXJuYWwuYXBwLnJ1bnRpbW" +
      "Uuc2NoZWR1bGUudHJpZ2dlci5EZWZhdWx0UHJvZ3JhbVN0YXR1c1RyaWdnZXJJbmZvAAAAA" +
      "AAAAAEMAAB4cHctACZ0ZXN0RGVzZXJpYWxpemF0aW9uVmVyc2lvbmluZ05hbWVzcGFjZQAD" +
      "QXBwfnIAIGlvLmNkYXAuY2RhcC5hcGkuYXBwLlByb2dyYW1UeXBlAAAAAAAAAAASAAB4cgA" +
      "OamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAIV09SS0ZMT1d3NgAOQWxsUHJvZ3JhbX" +
      "NBcHAAJDc4ZmU1MmMxLTM5MjEtMTFlYy04Y2MxLTAwMDAwMDc3OWM0Mn5yAB5pby5jZGFwL" +
      "mNkYXAuYXBpLlByb2dyYW1TdGF0dXMAAAAAAAAAABIAAHhxAH4AA3QACUNPTVBMRVRFRHB3" +
      "BAAAAAB4";

    // Verify that we can always deserialize.
    DefaultProgramStatusTriggerInfo deserializedTriggerInfo =
      (DefaultProgramStatusTriggerInfo) deSerializeFromStringBase64(serialized);
    Assert.assertEquals(namespace, deserializedTriggerInfo.getNamespace());
    Assert.assertEquals(AllProgramsApp.NAME, deserializedTriggerInfo.getApplicationName());
    Assert.assertEquals(ProgramType.WORKFLOW, deserializedTriggerInfo.getProgramType());
    Assert.assertEquals(AllProgramsApp.class.getSimpleName(), deserializedTriggerInfo.getProgram());
    Assert.assertTrue(runId.equals(deserializedTriggerInfo.getRunId()));
  }


  public static Object deSerializeFromStringBase64(String s) throws IOException, ClassNotFoundException {
    byte[] data = Base64.getDecoder().decode(s);
    Object o = null;
    try (ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(data))) {
      o = is.readObject();
    }
    return o;
  }

  public static String serializeToStringBase64(Serializable o) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
      oos.writeObject(o);
    }
    return Base64.getEncoder().encodeToString(os.toByteArray());
  }
}