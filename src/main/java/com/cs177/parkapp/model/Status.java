package com.cs177.parkapp.model;

public enum Status {
  OPEN {
    @Override
    public String toString() {
      return "Open";
    }
  },
  CLOSED {
    @Override
    public String toString() {
      return "Closed";
    }
  }
}
