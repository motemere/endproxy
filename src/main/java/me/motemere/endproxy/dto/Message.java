package me.motemere.endproxy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Message {

  private Integer sessionId;
  private Long entryPointTimestamp;
  private Long middleProxyTimestamp;
  private Long endProxyTimestamp;
  private Long finalTimestamp;

  /**
   * Override toString method.
   *
   * @return String representation of this object.
   */
  @Override
  public String toString() {
    return "{"
        + "sessionId=" + sessionId
        + ", entryPointTimestamp=" + entryPointTimestamp
        + ", middleProxyTimestamp=" + middleProxyTimestamp
        + ", endProxyTimestamp=" + endProxyTimestamp
        + ", finalTimestamp=" + finalTimestamp
        + '}';
  }

  /**
   * Make JSON string from this object.
   *
   * @return JSON string.
   */
  public String toJson() {
    return "{"
        + "\"sessionId\":" + sessionId
        + ",\"entryPointTimestamp\":" + entryPointTimestamp
        + ",\"middleProxyTimestamp\":" + middleProxyTimestamp
        + ",\"endProxyTimestamp\":" + endProxyTimestamp
        + ",\"finalTimestamp\":" + finalTimestamp
        + '}';
  }
}
