/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-08-17")
public class FetchBuildGraphResponse implements org.apache.thrift.TBase<FetchBuildGraphResponse, FetchBuildGraphResponse._Fields>, java.io.Serializable, Cloneable, Comparable<FetchBuildGraphResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FetchBuildGraphResponse");

  private static final org.apache.thrift.protocol.TField BUILD_GRAPH_FIELD_DESC = new org.apache.thrift.protocol.TField("buildGraph", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new FetchBuildGraphResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new FetchBuildGraphResponseTupleSchemeFactory());
  }

  public ByteBuffer buildGraph; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BUILD_GRAPH((short)1, "buildGraph");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // BUILD_GRAPH
          return BUILD_GRAPH;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.BUILD_GRAPH};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BUILD_GRAPH, new org.apache.thrift.meta_data.FieldMetaData("buildGraph", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FetchBuildGraphResponse.class, metaDataMap);
  }

  public FetchBuildGraphResponse() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FetchBuildGraphResponse(FetchBuildGraphResponse other) {
    if (other.isSetBuildGraph()) {
      this.buildGraph = org.apache.thrift.TBaseHelper.copyBinary(other.buildGraph);
    }
  }

  public FetchBuildGraphResponse deepCopy() {
    return new FetchBuildGraphResponse(this);
  }

  @Override
  public void clear() {
    this.buildGraph = null;
  }

  public byte[] getBuildGraph() {
    setBuildGraph(org.apache.thrift.TBaseHelper.rightSize(buildGraph));
    return buildGraph == null ? null : buildGraph.array();
  }

  public ByteBuffer bufferForBuildGraph() {
    return org.apache.thrift.TBaseHelper.copyBinary(buildGraph);
  }

  public FetchBuildGraphResponse setBuildGraph(byte[] buildGraph) {
    this.buildGraph = buildGraph == null ? (ByteBuffer)null : ByteBuffer.wrap(Arrays.copyOf(buildGraph, buildGraph.length));
    return this;
  }

  public FetchBuildGraphResponse setBuildGraph(ByteBuffer buildGraph) {
    this.buildGraph = org.apache.thrift.TBaseHelper.copyBinary(buildGraph);
    return this;
  }

  public void unsetBuildGraph() {
    this.buildGraph = null;
  }

  /** Returns true if field buildGraph is set (has been assigned a value) and false otherwise */
  public boolean isSetBuildGraph() {
    return this.buildGraph != null;
  }

  public void setBuildGraphIsSet(boolean value) {
    if (!value) {
      this.buildGraph = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BUILD_GRAPH:
      if (value == null) {
        unsetBuildGraph();
      } else {
        setBuildGraph((ByteBuffer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BUILD_GRAPH:
      return getBuildGraph();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BUILD_GRAPH:
      return isSetBuildGraph();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof FetchBuildGraphResponse)
      return this.equals((FetchBuildGraphResponse)that);
    return false;
  }

  public boolean equals(FetchBuildGraphResponse that) {
    if (that == null)
      return false;

    boolean this_present_buildGraph = true && this.isSetBuildGraph();
    boolean that_present_buildGraph = true && that.isSetBuildGraph();
    if (this_present_buildGraph || that_present_buildGraph) {
      if (!(this_present_buildGraph && that_present_buildGraph))
        return false;
      if (!this.buildGraph.equals(that.buildGraph))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_buildGraph = true && (isSetBuildGraph());
    list.add(present_buildGraph);
    if (present_buildGraph)
      list.add(buildGraph);

    return list.hashCode();
  }

  @Override
  public int compareTo(FetchBuildGraphResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetBuildGraph()).compareTo(other.isSetBuildGraph());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBuildGraph()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.buildGraph, other.buildGraph);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("FetchBuildGraphResponse(");
    boolean first = true;

    if (isSetBuildGraph()) {
      sb.append("buildGraph:");
      if (this.buildGraph == null) {
        sb.append("null");
      } else {
        org.apache.thrift.TBaseHelper.toString(this.buildGraph, sb);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class FetchBuildGraphResponseStandardSchemeFactory implements SchemeFactory {
    public FetchBuildGraphResponseStandardScheme getScheme() {
      return new FetchBuildGraphResponseStandardScheme();
    }
  }

  private static class FetchBuildGraphResponseStandardScheme extends StandardScheme<FetchBuildGraphResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FetchBuildGraphResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // BUILD_GRAPH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.buildGraph = iprot.readBinary();
              struct.setBuildGraphIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, FetchBuildGraphResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.buildGraph != null) {
        if (struct.isSetBuildGraph()) {
          oprot.writeFieldBegin(BUILD_GRAPH_FIELD_DESC);
          oprot.writeBinary(struct.buildGraph);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FetchBuildGraphResponseTupleSchemeFactory implements SchemeFactory {
    public FetchBuildGraphResponseTupleScheme getScheme() {
      return new FetchBuildGraphResponseTupleScheme();
    }
  }

  private static class FetchBuildGraphResponseTupleScheme extends TupleScheme<FetchBuildGraphResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FetchBuildGraphResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetBuildGraph()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetBuildGraph()) {
        oprot.writeBinary(struct.buildGraph);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FetchBuildGraphResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.buildGraph = iprot.readBinary();
        struct.setBuildGraphIsSet(true);
      }
    }
  }

}
