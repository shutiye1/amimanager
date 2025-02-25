/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package safariami.manager.prismtoken1;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@jakarta.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-02-03")
public class VerifyResult implements org.apache.thrift.TBase<VerifyResult, VerifyResult._Fields>, java.io.Serializable, Cloneable, Comparable<VerifyResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("VerifyResult");

  private static final org.apache.thrift.protocol.TField VALIDATION_RESULT_FIELD_DESC = new org.apache.thrift.protocol.TField("validationResult", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("token", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField METER_TEST_TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("meterTestToken", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new VerifyResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new VerifyResultTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String validationResult; // required
  public @org.apache.thrift.annotation.Nullable Token token; // optional
  public @org.apache.thrift.annotation.Nullable MeterTestToken meterTestToken; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    VALIDATION_RESULT((short)1, "validationResult"),
    TOKEN((short)2, "token"),
    METER_TEST_TOKEN((short)3, "meterTestToken");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // VALIDATION_RESULT
          return VALIDATION_RESULT;
        case 2: // TOKEN
          return TOKEN;
        case 3: // METER_TEST_TOKEN
          return METER_TEST_TOKEN;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.TOKEN,_Fields.METER_TEST_TOKEN};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.VALIDATION_RESULT, new org.apache.thrift.meta_data.FieldMetaData("validationResult", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TOKEN, new org.apache.thrift.meta_data.FieldMetaData("token", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Token.class)));
    tmpMap.put(_Fields.METER_TEST_TOKEN, new org.apache.thrift.meta_data.FieldMetaData("meterTestToken", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, MeterTestToken.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(VerifyResult.class, metaDataMap);
  }

  public VerifyResult() {
  }

  public VerifyResult(
    java.lang.String validationResult)
  {
    this();
    this.validationResult = validationResult;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public VerifyResult(VerifyResult other) {
    if (other.isSetValidationResult()) {
      this.validationResult = other.validationResult;
    }
    if (other.isSetToken()) {
      this.token = new Token(other.token);
    }
    if (other.isSetMeterTestToken()) {
      this.meterTestToken = new MeterTestToken(other.meterTestToken);
    }
  }

  public VerifyResult deepCopy() {
    return new VerifyResult(this);
  }

  @Override
  public void clear() {
    this.validationResult = null;
    this.token = null;
    this.meterTestToken = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getValidationResult() {
    return this.validationResult;
  }

  public VerifyResult setValidationResult(@org.apache.thrift.annotation.Nullable java.lang.String validationResult) {
    this.validationResult = validationResult;
    return this;
  }

  public void unsetValidationResult() {
    this.validationResult = null;
  }

  /** Returns true if field validationResult is set (has been assigned a value) and false otherwise */
  public boolean isSetValidationResult() {
    return this.validationResult != null;
  }

  public void setValidationResultIsSet(boolean value) {
    if (!value) {
      this.validationResult = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public Token getToken() {
    return this.token;
  }

  public VerifyResult setToken(@org.apache.thrift.annotation.Nullable Token token) {
    this.token = token;
    return this;
  }

  public void unsetToken() {
    this.token = null;
  }

  /** Returns true if field token is set (has been assigned a value) and false otherwise */
  public boolean isSetToken() {
    return this.token != null;
  }

  public void setTokenIsSet(boolean value) {
    if (!value) {
      this.token = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public MeterTestToken getMeterTestToken() {
    return this.meterTestToken;
  }

  public VerifyResult setMeterTestToken(@org.apache.thrift.annotation.Nullable MeterTestToken meterTestToken) {
    this.meterTestToken = meterTestToken;
    return this;
  }

  public void unsetMeterTestToken() {
    this.meterTestToken = null;
  }

  /** Returns true if field meterTestToken is set (has been assigned a value) and false otherwise */
  public boolean isSetMeterTestToken() {
    return this.meterTestToken != null;
  }

  public void setMeterTestTokenIsSet(boolean value) {
    if (!value) {
      this.meterTestToken = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case VALIDATION_RESULT:
      if (value == null) {
        unsetValidationResult();
      } else {
        setValidationResult((java.lang.String)value);
      }
      break;

    case TOKEN:
      if (value == null) {
        unsetToken();
      } else {
        setToken((Token)value);
      }
      break;

    case METER_TEST_TOKEN:
      if (value == null) {
        unsetMeterTestToken();
      } else {
        setMeterTestToken((MeterTestToken)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case VALIDATION_RESULT:
      return getValidationResult();

    case TOKEN:
      return getToken();

    case METER_TEST_TOKEN:
      return getMeterTestToken();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case VALIDATION_RESULT:
      return isSetValidationResult();
    case TOKEN:
      return isSetToken();
    case METER_TEST_TOKEN:
      return isSetMeterTestToken();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof VerifyResult)
      return this.equals((VerifyResult)that);
    return false;
  }

  public boolean equals(VerifyResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_validationResult = true && this.isSetValidationResult();
    boolean that_present_validationResult = true && that.isSetValidationResult();
    if (this_present_validationResult || that_present_validationResult) {
      if (!(this_present_validationResult && that_present_validationResult))
        return false;
      if (!this.validationResult.equals(that.validationResult))
        return false;
    }

    boolean this_present_token = true && this.isSetToken();
    boolean that_present_token = true && that.isSetToken();
    if (this_present_token || that_present_token) {
      if (!(this_present_token && that_present_token))
        return false;
      if (!this.token.equals(that.token))
        return false;
    }

    boolean this_present_meterTestToken = true && this.isSetMeterTestToken();
    boolean that_present_meterTestToken = true && that.isSetMeterTestToken();
    if (this_present_meterTestToken || that_present_meterTestToken) {
      if (!(this_present_meterTestToken && that_present_meterTestToken))
        return false;
      if (!this.meterTestToken.equals(that.meterTestToken))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetValidationResult()) ? 131071 : 524287);
    if (isSetValidationResult())
      hashCode = hashCode * 8191 + validationResult.hashCode();

    hashCode = hashCode * 8191 + ((isSetToken()) ? 131071 : 524287);
    if (isSetToken())
      hashCode = hashCode * 8191 + token.hashCode();

    hashCode = hashCode * 8191 + ((isSetMeterTestToken()) ? 131071 : 524287);
    if (isSetMeterTestToken())
      hashCode = hashCode * 8191 + meterTestToken.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(VerifyResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetValidationResult()).compareTo(other.isSetValidationResult());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValidationResult()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.validationResult, other.validationResult);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetToken()).compareTo(other.isSetToken());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToken()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.token, other.token);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMeterTestToken()).compareTo(other.isSetMeterTestToken());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMeterTestToken()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.meterTestToken, other.meterTestToken);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("VerifyResult(");
    boolean first = true;

    sb.append("validationResult:");
    if (this.validationResult == null) {
      sb.append("null");
    } else {
      sb.append(this.validationResult);
    }
    first = false;
    if (isSetToken()) {
      if (!first) sb.append(", ");
      sb.append("token:");
      if (this.token == null) {
        sb.append("null");
      } else {
        sb.append(this.token);
      }
      first = false;
    }
    if (isSetMeterTestToken()) {
      if (!first) sb.append(", ");
      sb.append("meterTestToken:");
      if (this.meterTestToken == null) {
        sb.append("null");
      } else {
        sb.append(this.meterTestToken);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (validationResult == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'validationResult' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (token != null) {
      token.validate();
    }
    if (meterTestToken != null) {
      meterTestToken.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class VerifyResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public VerifyResultStandardScheme getScheme() {
      return new VerifyResultStandardScheme();
    }
  }

  private static class VerifyResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<VerifyResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, VerifyResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // VALIDATION_RESULT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.validationResult = iprot.readString();
              struct.setValidationResultIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOKEN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.token = new Token();
              struct.token.read(iprot);
              struct.setTokenIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // METER_TEST_TOKEN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.meterTestToken = new MeterTestToken();
              struct.meterTestToken.read(iprot);
              struct.setMeterTestTokenIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, VerifyResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.validationResult != null) {
        oprot.writeFieldBegin(VALIDATION_RESULT_FIELD_DESC);
        oprot.writeString(struct.validationResult);
        oprot.writeFieldEnd();
      }
      if (struct.token != null) {
        if (struct.isSetToken()) {
          oprot.writeFieldBegin(TOKEN_FIELD_DESC);
          struct.token.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.meterTestToken != null) {
        if (struct.isSetMeterTestToken()) {
          oprot.writeFieldBegin(METER_TEST_TOKEN_FIELD_DESC);
          struct.meterTestToken.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class VerifyResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public VerifyResultTupleScheme getScheme() {
      return new VerifyResultTupleScheme();
    }
  }

  private static class VerifyResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<VerifyResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, VerifyResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.validationResult);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetToken()) {
        optionals.set(0);
      }
      if (struct.isSetMeterTestToken()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetToken()) {
        struct.token.write(oprot);
      }
      if (struct.isSetMeterTestToken()) {
        struct.meterTestToken.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, VerifyResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.validationResult = iprot.readString();
      struct.setValidationResultIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.token = new Token();
        struct.token.read(iprot);
        struct.setTokenIsSet(true);
      }
      if (incoming.get(1)) {
        struct.meterTestToken = new MeterTestToken();
        struct.meterTestToken.read(iprot);
        struct.setMeterTestTokenIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

