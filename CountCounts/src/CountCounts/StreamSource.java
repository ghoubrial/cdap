package CountCounts;


import com.continuuity.api.annotation.UseDataSet;
import com.continuuity.api.common.Bytes;
import com.continuuity.api.flow.flowlet.AbstractFlowlet;
import com.continuuity.api.flow.flowlet.FlowletSpecification;
import com.continuuity.api.flow.flowlet.OutputEmitter;
import com.continuuity.api.flow.flowlet.StreamEvent;

import java.nio.ByteBuffer;

public class StreamSource extends AbstractFlowlet {
  static String keyTotal = ":sourceTotal:";

  private OutputEmitter<String> output;

  @UseDataSet(Common.tableName)
  CounterTable counters;

  public FlowletSpecification configure() {
    return FlowletSpecification.Builder.with()
      .setName("text")
      .setDescription("")
      .useDataSet(Common.tableName)
      .build();
  }

  public StreamSource() {
    super("source");
  }

  public void process(StreamEvent event) {
    if (Common.debug) {
      System.out.println(this.getClass().getSimpleName() + ": Received event " + event);
    }
    ByteBuffer buf = event.getBody();
    byte[] body = Bytes.toBytes(event.getBody());
    String text = body == null ? null :new String(body);

    if (Common.debug) {
      System.out.println(this.getClass().getSimpleName() + ": Emitting " + text);
    }

    if (Common.count) {
      // emit an increment for the total number of documents ingested
      this.counters.increment(keyTotal);
    }

    output.emit(text);
  }
}