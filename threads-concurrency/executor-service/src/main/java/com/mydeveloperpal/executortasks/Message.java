package com.mydeveloperpal.executortasks;

import java.util.Objects;

public class Message {

  private final int value;
  private int retryCount = 0;

  public Message(int value) {
    this.value = value;
  }

  public Message(int value, int retryCount) {
    this.value = value;
    this.retryCount = retryCount;
  }

  public int getValue() {
    return value;
  }

  public int getRetryCount() {
    return retryCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Message message = (Message) o;
    return value == message.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  public boolean isWithinRetryCountLimit(int retryCountLimit) {
    return this.retryCount < retryCountLimit;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("");
    sb.append("value=").append(value);
    sb.append(", retryCount=").append(retryCount);
    return sb.toString();
  }
}
