/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.continuuity.data.operation.executor.remote.stubs;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TTransaction2 implements org.apache.thrift.TBase<TTransaction2, TTransaction2._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TTransaction2");

  private static final org.apache.thrift.protocol.TField WRITE_POINTER_FIELD_DESC = new org.apache.thrift.protocol.TField("writePointer", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField READ_POINTER_FIELD_DESC = new org.apache.thrift.protocol.TField("readPointer", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField EXCLUDES_FIELD_DESC = new org.apache.thrift.protocol.TField("excludes", org.apache.thrift.protocol.TType.SET, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TTransaction2StandardSchemeFactory());
    schemes.put(TupleScheme.class, new TTransaction2TupleSchemeFactory());
  }

  public long writePointer; // required
  public long readPointer; // required
  public Set<Long> excludes; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WRITE_POINTER((short)1, "writePointer"),
    READ_POINTER((short)2, "readPointer"),
    EXCLUDES((short)3, "excludes");

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
        case 1: // WRITE_POINTER
          return WRITE_POINTER;
        case 2: // READ_POINTER
          return READ_POINTER;
        case 3: // EXCLUDES
          return EXCLUDES;
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
  private static final int __WRITEPOINTER_ISSET_ID = 0;
  private static final int __READPOINTER_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.WRITE_POINTER, new org.apache.thrift.meta_data.FieldMetaData("writePointer", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.READ_POINTER, new org.apache.thrift.meta_data.FieldMetaData("readPointer", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EXCLUDES, new org.apache.thrift.meta_data.FieldMetaData("excludes", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TTransaction2.class, metaDataMap);
  }

  public TTransaction2() {
  }

  public TTransaction2(
    long writePointer,
    long readPointer,
    Set<Long> excludes)
  {
    this();
    this.writePointer = writePointer;
    setWritePointerIsSet(true);
    this.readPointer = readPointer;
    setReadPointerIsSet(true);
    this.excludes = excludes;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TTransaction2(TTransaction2 other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.writePointer = other.writePointer;
    this.readPointer = other.readPointer;
    if (other.isSetExcludes()) {
      Set<Long> __this__excludes = new HashSet<Long>();
      for (Long other_element : other.excludes) {
        __this__excludes.add(other_element);
      }
      this.excludes = __this__excludes;
    }
  }

  public TTransaction2 deepCopy() {
    return new TTransaction2(this);
  }

  @Override
  public void clear() {
    setWritePointerIsSet(false);
    this.writePointer = 0;
    setReadPointerIsSet(false);
    this.readPointer = 0;
    this.excludes = null;
  }

  public long getWritePointer() {
    return this.writePointer;
  }

  public TTransaction2 setWritePointer(long writePointer) {
    this.writePointer = writePointer;
    setWritePointerIsSet(true);
    return this;
  }

  public void unsetWritePointer() {
    __isset_bit_vector.clear(__WRITEPOINTER_ISSET_ID);
  }

  /** Returns true if field writePointer is set (has been assigned a value) and false otherwise */
  public boolean isSetWritePointer() {
    return __isset_bit_vector.get(__WRITEPOINTER_ISSET_ID);
  }

  public void setWritePointerIsSet(boolean value) {
    __isset_bit_vector.set(__WRITEPOINTER_ISSET_ID, value);
  }

  public long getReadPointer() {
    return this.readPointer;
  }

  public TTransaction2 setReadPointer(long readPointer) {
    this.readPointer = readPointer;
    setReadPointerIsSet(true);
    return this;
  }

  public void unsetReadPointer() {
    __isset_bit_vector.clear(__READPOINTER_ISSET_ID);
  }

  /** Returns true if field readPointer is set (has been assigned a value) and false otherwise */
  public boolean isSetReadPointer() {
    return __isset_bit_vector.get(__READPOINTER_ISSET_ID);
  }

  public void setReadPointerIsSet(boolean value) {
    __isset_bit_vector.set(__READPOINTER_ISSET_ID, value);
  }

  public int getExcludesSize() {
    return (this.excludes == null) ? 0 : this.excludes.size();
  }

  public java.util.Iterator<Long> getExcludesIterator() {
    return (this.excludes == null) ? null : this.excludes.iterator();
  }

  public void addToExcludes(long elem) {
    if (this.excludes == null) {
      this.excludes = new HashSet<Long>();
    }
    this.excludes.add(elem);
  }

  public Set<Long> getExcludes() {
    return this.excludes;
  }

  public TTransaction2 setExcludes(Set<Long> excludes) {
    this.excludes = excludes;
    return this;
  }

  public void unsetExcludes() {
    this.excludes = null;
  }

  /** Returns true if field excludes is set (has been assigned a value) and false otherwise */
  public boolean isSetExcludes() {
    return this.excludes != null;
  }

  public void setExcludesIsSet(boolean value) {
    if (!value) {
      this.excludes = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case WRITE_POINTER:
      if (value == null) {
        unsetWritePointer();
      } else {
        setWritePointer((Long)value);
      }
      break;

    case READ_POINTER:
      if (value == null) {
        unsetReadPointer();
      } else {
        setReadPointer((Long)value);
      }
      break;

    case EXCLUDES:
      if (value == null) {
        unsetExcludes();
      } else {
        setExcludes((Set<Long>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case WRITE_POINTER:
      return Long.valueOf(getWritePointer());

    case READ_POINTER:
      return Long.valueOf(getReadPointer());

    case EXCLUDES:
      return getExcludes();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case WRITE_POINTER:
      return isSetWritePointer();
    case READ_POINTER:
      return isSetReadPointer();
    case EXCLUDES:
      return isSetExcludes();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TTransaction2)
      return this.equals((TTransaction2)that);
    return false;
  }

  public boolean equals(TTransaction2 that) {
    if (that == null)
      return false;

    boolean this_present_writePointer = true;
    boolean that_present_writePointer = true;
    if (this_present_writePointer || that_present_writePointer) {
      if (!(this_present_writePointer && that_present_writePointer))
        return false;
      if (this.writePointer != that.writePointer)
        return false;
    }

    boolean this_present_readPointer = true;
    boolean that_present_readPointer = true;
    if (this_present_readPointer || that_present_readPointer) {
      if (!(this_present_readPointer && that_present_readPointer))
        return false;
      if (this.readPointer != that.readPointer)
        return false;
    }

    boolean this_present_excludes = true && this.isSetExcludes();
    boolean that_present_excludes = true && that.isSetExcludes();
    if (this_present_excludes || that_present_excludes) {
      if (!(this_present_excludes && that_present_excludes))
        return false;
      if (!this.excludes.equals(that.excludes))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TTransaction2 other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TTransaction2 typedOther = (TTransaction2)other;

    lastComparison = Boolean.valueOf(isSetWritePointer()).compareTo(typedOther.isSetWritePointer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWritePointer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.writePointer, typedOther.writePointer);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReadPointer()).compareTo(typedOther.isSetReadPointer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReadPointer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.readPointer, typedOther.readPointer);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExcludes()).compareTo(typedOther.isSetExcludes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExcludes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.excludes, typedOther.excludes);
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
    StringBuilder sb = new StringBuilder("TTransaction2(");
    boolean first = true;

    sb.append("writePointer:");
    sb.append(this.writePointer);
    first = false;
    if (!first) sb.append(", ");
    sb.append("readPointer:");
    sb.append(this.readPointer);
    first = false;
    if (!first) sb.append(", ");
    sb.append("excludes:");
    if (this.excludes == null) {
      sb.append("null");
    } else {
      sb.append(this.excludes);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TTransaction2StandardSchemeFactory implements SchemeFactory {
    public TTransaction2StandardScheme getScheme() {
      return new TTransaction2StandardScheme();
    }
  }

  private static class TTransaction2StandardScheme extends StandardScheme<TTransaction2> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TTransaction2 struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WRITE_POINTER
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.writePointer = iprot.readI64();
              struct.setWritePointerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // READ_POINTER
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.readPointer = iprot.readI64();
              struct.setReadPointerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXCLUDES
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set132 = iprot.readSetBegin();
                struct.excludes = new HashSet<Long>(2*_set132.size);
                for (int _i133 = 0; _i133 < _set132.size; ++_i133)
                {
                  long _elem134; // required
                  _elem134 = iprot.readI64();
                  struct.excludes.add(_elem134);
                }
                iprot.readSetEnd();
              }
              struct.setExcludesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TTransaction2 struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(WRITE_POINTER_FIELD_DESC);
      oprot.writeI64(struct.writePointer);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(READ_POINTER_FIELD_DESC);
      oprot.writeI64(struct.readPointer);
      oprot.writeFieldEnd();
      if (struct.excludes != null) {
        oprot.writeFieldBegin(EXCLUDES_FIELD_DESC);
        {
          oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I64, struct.excludes.size()));
          for (long _iter135 : struct.excludes)
          {
            oprot.writeI64(_iter135);
          }
          oprot.writeSetEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TTransaction2TupleSchemeFactory implements SchemeFactory {
    public TTransaction2TupleScheme getScheme() {
      return new TTransaction2TupleScheme();
    }
  }

  private static class TTransaction2TupleScheme extends TupleScheme<TTransaction2> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TTransaction2 struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetWritePointer()) {
        optionals.set(0);
      }
      if (struct.isSetReadPointer()) {
        optionals.set(1);
      }
      if (struct.isSetExcludes()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetWritePointer()) {
        oprot.writeI64(struct.writePointer);
      }
      if (struct.isSetReadPointer()) {
        oprot.writeI64(struct.readPointer);
      }
      if (struct.isSetExcludes()) {
        {
          oprot.writeI32(struct.excludes.size());
          for (long _iter136 : struct.excludes)
          {
            oprot.writeI64(_iter136);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TTransaction2 struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.writePointer = iprot.readI64();
        struct.setWritePointerIsSet(true);
      }
      if (incoming.get(1)) {
        struct.readPointer = iprot.readI64();
        struct.setReadPointerIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TSet _set137 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.excludes = new HashSet<Long>(2*_set137.size);
          for (int _i138 = 0; _i138 < _set137.size; ++_i138)
          {
            long _elem139; // required
            _elem139 = iprot.readI64();
            struct.excludes.add(_elem139);
          }
        }
        struct.setExcludesIsSet(true);
      }
    }
  }

}

