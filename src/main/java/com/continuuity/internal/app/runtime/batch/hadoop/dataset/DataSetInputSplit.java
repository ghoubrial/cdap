package com.continuuity.internal.app.runtime.batch.hadoop.dataset;

import com.continuuity.api.data.batch.Split;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

class DataSetInputSplit extends InputSplit implements Writable {
  private Split split;

  public DataSetInputSplit() {
  }

  public DataSetInputSplit(final Split split) {
    this.split = split;
  }

  public Split getSplit() {
    return split;
  }

  @Override
  public long getLength() throws IOException, InterruptedException {
    // By default all splits are of equal size
    return 0;
  }

  @Override
  public String[] getLocations() throws IOException, InterruptedException {
    // By default splits locations are not provided (all operations go thru OpEx)
    return new String[0];
  }

  @Override
  public void write(final DataOutput out) throws IOException {
    Text.writeString(out, split.getClass().getName());
    String ser = new Gson().toJson(split);
    Text.writeString(out, ser);
  }

  @Override
  public void readFields(final DataInput in) throws IOException {
    try {
      Class<? extends Split> splitClass = (Class<Split>) Class.forName(Text.readString(in));
      split = new Gson().fromJson(Text.readString(in), splitClass);
    } catch(ClassNotFoundException e) {
      throw Throwables.propagate(e);
    }
  }
}
