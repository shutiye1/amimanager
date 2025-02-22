/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package safariami.manager.prismtoken1;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@jakarta.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-02-03")
public class MeterConfigAdvice implements org.apache.thrift.TBase<MeterConfigAdvice, MeterConfigAdvice._Fields>, java.io.Serializable, Cloneable, Comparable<MeterConfigAdvice> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MeterConfigAdvice");

  private static final org.apache.thrift.protocol.TField TO_SGC_FIELD_DESC = new org.apache.thrift.protocol.TField("toSgc", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TO_KRN_FIELD_DESC = new org.apache.thrift.protocol.TField("toKrn", org.apache.thrift.protocol.TType.I16, (short)2);
  private static final org.apache.thrift.protocol.TField TO_TI_FIELD_DESC = new org.apache.thrift.protocol.TField("toTi", org.apache.thrift.protocol.TType.I16, (short)3);
  private static final org.apache.thrift.protocol.TField TO_KEN_FIELD_DESC = new org.apache.thrift.protocol.TField("toKen", org.apache.thrift.protocol.TType.I16, (short)4);
  private static final org.apache.thrift.protocol.TField ID_RECORD_FIELD_DESC = new org.apache.thrift.protocol.TField("idRecord", org.apache.thrift.protocol.TType.STRING, (short)10);
  private static final org.apache.thrift.protocol.TField RECORD2_FIELD_DESC = new org.apache.thrift.protocol.TField("record2", org.apache.thrift.protocol.TType.STRING, (short)11);
  private static final org.apache.thrift.protocol.TField ROLLOVER_FIELD_DESC = new org.apache.thrift.protocol.TField("rollover", org.apache.thrift.protocol.TType.BOOL, (short)20);
  private static final org.apache.thrift.protocol.TField TO_VK_KCV_FIELD_DESC = new org.apache.thrift.protocol.TField("toVkKcv", org.apache.thrift.protocol.TType.STRING, (short)21);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new MeterConfigAdviceStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new MeterConfigAdviceTupleSchemeFactory();

  public int toSgc; // required
  public short toKrn; // required
  public short toTi; // required
  public short toKen; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String idRecord; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String record2; // required
  public boolean rollover; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String toVkKcv; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TO_SGC((short)1, "toSgc"),
    TO_KRN((short)2, "toKrn"),
    TO_TI((short)3, "toTi"),
    TO_KEN((short)4, "toKen"),
    ID_RECORD((short)10, "idRecord"),
    RECORD2((short)11, "record2"),
    ROLLOVER((short)20, "rollover"),
    TO_VK_KCV((short)21, "toVkKcv");

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
        case 1: // TO_SGC
          return TO_SGC;
        case 2: // TO_KRN
          return TO_KRN;
        case 3: // TO_TI
          return TO_TI;
        case 4: // TO_KEN
          return TO_KEN;
        case 10: // ID_RECORD
          return ID_RECORD;
        case 11: // RECORD2
          return RECORD2;
        case 20: // ROLLOVER
          return ROLLOVER;
        case 21: // TO_VK_KCV
          return TO_VK_KCV;
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
  private static final int __TOSGC_ISSET_ID = 0;
  private static final int __TOKRN_ISSET_ID = 1;
  private static final int __TOTI_ISSET_ID = 2;
  private static final int __TOKEN_ISSET_ID = 3;
  private static final int __ROLLOVER_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TO_SGC, new org.apache.thrift.meta_data.FieldMetaData("toSgc", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TO_KRN, new org.apache.thrift.meta_data.FieldMetaData("toKrn", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.TO_TI, new org.apache.thrift.meta_data.FieldMetaData("toTi", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.TO_KEN, new org.apache.thrift.meta_data.FieldMetaData("toKen", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.ID_RECORD, new org.apache.thrift.meta_data.FieldMetaData("idRecord", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RECORD2, new org.apache.thrift.meta_data.FieldMetaData("record2", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLLOVER, new org.apache.thrift.meta_data.FieldMetaData("rollover", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.TO_VK_KCV, new org.apache.thrift.meta_data.FieldMetaData("toVkKcv", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MeterConfigAdvice.class, metaDataMap);
  }

  public MeterConfigAdvice() {
  }

  public MeterConfigAdvice(
    int toSgc,
    short toKrn,
    short toTi,
    short toKen,
    java.lang.String idRecord,
    java.lang.String record2,
    boolean rollover,
    java.lang.String toVkKcv)
  {
    this();
    this.toSgc = toSgc;
    setToSgcIsSet(true);
    this.toKrn = toKrn;
    setToKrnIsSet(true);
    this.toTi = toTi;
    setToTiIsSet(true);
    this.toKen = toKen;
    setToKenIsSet(true);
    this.idRecord = idRecord;
    this.record2 = record2;
    this.rollover = rollover;
    setRolloverIsSet(true);
    this.toVkKcv = toVkKcv;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MeterConfigAdvice(MeterConfigAdvice other) {
    __isset_bitfield = other.__isset_bitfield;
    this.toSgc = other.toSgc;
    this.toKrn = other.toKrn;
    this.toTi = other.toTi;
    this.toKen = other.toKen;
    if (other.isSetIdRecord()) {
      this.idRecord = other.idRecord;
    }
    if (other.isSetRecord2()) {
      this.record2 = other.record2;
    }
    this.rollover = other.rollover;
    if (other.isSetToVkKcv()) {
      this.toVkKcv = other.toVkKcv;
    }
  }

  public MeterConfigAdvice deepCopy() {
    return new MeterConfigAdvice(this);
  }

  @Override
  public void clear() {
    setToSgcIsSet(false);
    this.toSgc = 0;
    setToKrnIsSet(false);
    this.toKrn = 0;
    setToTiIsSet(false);
    this.toTi = 0;
    setToKenIsSet(false);
    this.toKen = 0;
    this.idRecord = null;
    this.record2 = null;
    setRolloverIsSet(false);
    this.rollover = false;
    this.toVkKcv = null;
  }

  public int getToSgc() {
    return this.toSgc;
  }

  public MeterConfigAdvice setToSgc(int toSgc) {
    this.toSgc = toSgc;
    setToSgcIsSet(true);
    return this;
  }

  public void unsetToSgc() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOSGC_ISSET_ID);
  }

  /** Returns true if field toSgc is set (has been assigned a value) and false otherwise */
  public boolean isSetToSgc() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOSGC_ISSET_ID);
  }

  public void setToSgcIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOSGC_ISSET_ID, value);
  }

  public short getToKrn() {
    return this.toKrn;
  }

  public MeterConfigAdvice setToKrn(short toKrn) {
    this.toKrn = toKrn;
    setToKrnIsSet(true);
    return this;
  }

  public void unsetToKrn() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOKRN_ISSET_ID);
  }

  /** Returns true if field toKrn is set (has been assigned a value) and false otherwise */
  public boolean isSetToKrn() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOKRN_ISSET_ID);
  }

  public void setToKrnIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOKRN_ISSET_ID, value);
  }

  public short getToTi() {
    return this.toTi;
  }

  public MeterConfigAdvice setToTi(short toTi) {
    this.toTi = toTi;
    setToTiIsSet(true);
    return this;
  }

  public void unsetToTi() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOTI_ISSET_ID);
  }

  /** Returns true if field toTi is set (has been assigned a value) and false otherwise */
  public boolean isSetToTi() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOTI_ISSET_ID);
  }

  public void setToTiIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOTI_ISSET_ID, value);
  }

  public short getToKen() {
    return this.toKen;
  }

  public MeterConfigAdvice setToKen(short toKen) {
    this.toKen = toKen;
    setToKenIsSet(true);
    return this;
  }

  public void unsetToKen() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOKEN_ISSET_ID);
  }

  /** Returns true if field toKen is set (has been assigned a value) and false otherwise */
  public boolean isSetToKen() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOKEN_ISSET_ID);
  }

  public void setToKenIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOKEN_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getIdRecord() {
    return this.idRecord;
  }

  public MeterConfigAdvice setIdRecord(@org.apache.thrift.annotation.Nullable java.lang.String idRecord) {
    this.idRecord = idRecord;
    return this;
  }

  public void unsetIdRecord() {
    this.idRecord = null;
  }

  /** Returns true if field idRecord is set (has been assigned a value) and false otherwise */
  public boolean isSetIdRecord() {
    return this.idRecord != null;
  }

  public void setIdRecordIsSet(boolean value) {
    if (!value) {
      this.idRecord = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getRecord2() {
    return this.record2;
  }

  public MeterConfigAdvice setRecord2(@org.apache.thrift.annotation.Nullable java.lang.String record2) {
    this.record2 = record2;
    return this;
  }

  public void unsetRecord2() {
    this.record2 = null;
  }

  /** Returns true if field record2 is set (has been assigned a value) and false otherwise */
  public boolean isSetRecord2() {
    return this.record2 != null;
  }

  public void setRecord2IsSet(boolean value) {
    if (!value) {
      this.record2 = null;
    }
  }

  public boolean isRollover() {
    return this.rollover;
  }

  public MeterConfigAdvice setRollover(boolean rollover) {
    this.rollover = rollover;
    setRolloverIsSet(true);
    return this;
  }

  public void unsetRollover() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ROLLOVER_ISSET_ID);
  }

  /** Returns true if field rollover is set (has been assigned a value) and false otherwise */
  public boolean isSetRollover() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ROLLOVER_ISSET_ID);
  }

  public void setRolloverIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ROLLOVER_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getToVkKcv() {
    return this.toVkKcv;
  }

  public MeterConfigAdvice setToVkKcv(@org.apache.thrift.annotation.Nullable java.lang.String toVkKcv) {
    this.toVkKcv = toVkKcv;
    return this;
  }

  public void unsetToVkKcv() {
    this.toVkKcv = null;
  }

  /** Returns true if field toVkKcv is set (has been assigned a value) and false otherwise */
  public boolean isSetToVkKcv() {
    return this.toVkKcv != null;
  }

  public void setToVkKcvIsSet(boolean value) {
    if (!value) {
      this.toVkKcv = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TO_SGC:
      if (value == null) {
        unsetToSgc();
      } else {
        setToSgc((java.lang.Integer)value);
      }
      break;

    case TO_KRN:
      if (value == null) {
        unsetToKrn();
      } else {
        setToKrn((java.lang.Short)value);
      }
      break;

    case TO_TI:
      if (value == null) {
        unsetToTi();
      } else {
        setToTi((java.lang.Short)value);
      }
      break;

    case TO_KEN:
      if (value == null) {
        unsetToKen();
      } else {
        setToKen((java.lang.Short)value);
      }
      break;

    case ID_RECORD:
      if (value == null) {
        unsetIdRecord();
      } else {
        setIdRecord((java.lang.String)value);
      }
      break;

    case RECORD2:
      if (value == null) {
        unsetRecord2();
      } else {
        setRecord2((java.lang.String)value);
      }
      break;

    case ROLLOVER:
      if (value == null) {
        unsetRollover();
      } else {
        setRollover((java.lang.Boolean)value);
      }
      break;

    case TO_VK_KCV:
      if (value == null) {
        unsetToVkKcv();
      } else {
        setToVkKcv((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TO_SGC:
      return getToSgc();

    case TO_KRN:
      return getToKrn();

    case TO_TI:
      return getToTi();

    case TO_KEN:
      return getToKen();

    case ID_RECORD:
      return getIdRecord();

    case RECORD2:
      return getRecord2();

    case ROLLOVER:
      return isRollover();

    case TO_VK_KCV:
      return getToVkKcv();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TO_SGC:
      return isSetToSgc();
    case TO_KRN:
      return isSetToKrn();
    case TO_TI:
      return isSetToTi();
    case TO_KEN:
      return isSetToKen();
    case ID_RECORD:
      return isSetIdRecord();
    case RECORD2:
      return isSetRecord2();
    case ROLLOVER:
      return isSetRollover();
    case TO_VK_KCV:
      return isSetToVkKcv();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof MeterConfigAdvice)
      return this.equals((MeterConfigAdvice)that);
    return false;
  }

  public boolean equals(MeterConfigAdvice that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_toSgc = true;
    boolean that_present_toSgc = true;
    if (this_present_toSgc || that_present_toSgc) {
      if (!(this_present_toSgc && that_present_toSgc))
        return false;
      if (this.toSgc != that.toSgc)
        return false;
    }

    boolean this_present_toKrn = true;
    boolean that_present_toKrn = true;
    if (this_present_toKrn || that_present_toKrn) {
      if (!(this_present_toKrn && that_present_toKrn))
        return false;
      if (this.toKrn != that.toKrn)
        return false;
    }

    boolean this_present_toTi = true;
    boolean that_present_toTi = true;
    if (this_present_toTi || that_present_toTi) {
      if (!(this_present_toTi && that_present_toTi))
        return false;
      if (this.toTi != that.toTi)
        return false;
    }

    boolean this_present_toKen = true;
    boolean that_present_toKen = true;
    if (this_present_toKen || that_present_toKen) {
      if (!(this_present_toKen && that_present_toKen))
        return false;
      if (this.toKen != that.toKen)
        return false;
    }

    boolean this_present_idRecord = true && this.isSetIdRecord();
    boolean that_present_idRecord = true && that.isSetIdRecord();
    if (this_present_idRecord || that_present_idRecord) {
      if (!(this_present_idRecord && that_present_idRecord))
        return false;
      if (!this.idRecord.equals(that.idRecord))
        return false;
    }

    boolean this_present_record2 = true && this.isSetRecord2();
    boolean that_present_record2 = true && that.isSetRecord2();
    if (this_present_record2 || that_present_record2) {
      if (!(this_present_record2 && that_present_record2))
        return false;
      if (!this.record2.equals(that.record2))
        return false;
    }

    boolean this_present_rollover = true;
    boolean that_present_rollover = true;
    if (this_present_rollover || that_present_rollover) {
      if (!(this_present_rollover && that_present_rollover))
        return false;
      if (this.rollover != that.rollover)
        return false;
    }

    boolean this_present_toVkKcv = true && this.isSetToVkKcv();
    boolean that_present_toVkKcv = true && that.isSetToVkKcv();
    if (this_present_toVkKcv || that_present_toVkKcv) {
      if (!(this_present_toVkKcv && that_present_toVkKcv))
        return false;
      if (!this.toVkKcv.equals(that.toVkKcv))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + toSgc;

    hashCode = hashCode * 8191 + toKrn;

    hashCode = hashCode * 8191 + toTi;

    hashCode = hashCode * 8191 + toKen;

    hashCode = hashCode * 8191 + ((isSetIdRecord()) ? 131071 : 524287);
    if (isSetIdRecord())
      hashCode = hashCode * 8191 + idRecord.hashCode();

    hashCode = hashCode * 8191 + ((isSetRecord2()) ? 131071 : 524287);
    if (isSetRecord2())
      hashCode = hashCode * 8191 + record2.hashCode();

    hashCode = hashCode * 8191 + ((rollover) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetToVkKcv()) ? 131071 : 524287);
    if (isSetToVkKcv())
      hashCode = hashCode * 8191 + toVkKcv.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(MeterConfigAdvice other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetToSgc()).compareTo(other.isSetToSgc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToSgc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.toSgc, other.toSgc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetToKrn()).compareTo(other.isSetToKrn());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToKrn()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.toKrn, other.toKrn);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetToTi()).compareTo(other.isSetToTi());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToTi()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.toTi, other.toTi);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetToKen()).compareTo(other.isSetToKen());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToKen()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.toKen, other.toKen);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIdRecord()).compareTo(other.isSetIdRecord());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIdRecord()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.idRecord, other.idRecord);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRecord2()).compareTo(other.isSetRecord2());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRecord2()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.record2, other.record2);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRollover()).compareTo(other.isSetRollover());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRollover()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rollover, other.rollover);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetToVkKcv()).compareTo(other.isSetToVkKcv());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToVkKcv()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.toVkKcv, other.toVkKcv);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("MeterConfigAdvice(");
    boolean first = true;

    sb.append("toSgc:");
    sb.append(this.toSgc);
    first = false;
    if (!first) sb.append(", ");
    sb.append("toKrn:");
    sb.append(this.toKrn);
    first = false;
    if (!first) sb.append(", ");
    sb.append("toTi:");
    sb.append(this.toTi);
    first = false;
    if (!first) sb.append(", ");
    sb.append("toKen:");
    sb.append(this.toKen);
    first = false;
    if (!first) sb.append(", ");
    sb.append("idRecord:");
    if (this.idRecord == null) {
      sb.append("null");
    } else {
      sb.append(this.idRecord);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("record2:");
    if (this.record2 == null) {
      sb.append("null");
    } else {
      sb.append(this.record2);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("rollover:");
    sb.append(this.rollover);
    first = false;
    if (!first) sb.append(", ");
    sb.append("toVkKcv:");
    if (this.toVkKcv == null) {
      sb.append("null");
    } else {
      sb.append(this.toVkKcv);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'toSgc' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'toKrn' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'toTi' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'toKen' because it's a primitive and you chose the non-beans generator.
    if (idRecord == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'idRecord' was not present! Struct: " + toString());
    }
    if (record2 == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'record2' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'rollover' because it's a primitive and you chose the non-beans generator.
    if (toVkKcv == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'toVkKcv' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class MeterConfigAdviceStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MeterConfigAdviceStandardScheme getScheme() {
      return new MeterConfigAdviceStandardScheme();
    }
  }

  private static class MeterConfigAdviceStandardScheme extends org.apache.thrift.scheme.StandardScheme<MeterConfigAdvice> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MeterConfigAdvice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TO_SGC
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.toSgc = iprot.readI32();
              struct.setToSgcIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TO_KRN
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.toKrn = iprot.readI16();
              struct.setToKrnIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TO_TI
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.toTi = iprot.readI16();
              struct.setToTiIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TO_KEN
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.toKen = iprot.readI16();
              struct.setToKenIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // ID_RECORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.idRecord = iprot.readString();
              struct.setIdRecordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 11: // RECORD2
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.record2 = iprot.readString();
              struct.setRecord2IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 20: // ROLLOVER
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.rollover = iprot.readBool();
              struct.setRolloverIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 21: // TO_VK_KCV
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.toVkKcv = iprot.readString();
              struct.setToVkKcvIsSet(true);
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
      if (!struct.isSetToSgc()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'toSgc' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetToKrn()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'toKrn' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetToTi()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'toTi' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetToKen()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'toKen' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetRollover()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'rollover' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, MeterConfigAdvice struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TO_SGC_FIELD_DESC);
      oprot.writeI32(struct.toSgc);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TO_KRN_FIELD_DESC);
      oprot.writeI16(struct.toKrn);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TO_TI_FIELD_DESC);
      oprot.writeI16(struct.toTi);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TO_KEN_FIELD_DESC);
      oprot.writeI16(struct.toKen);
      oprot.writeFieldEnd();
      if (struct.idRecord != null) {
        oprot.writeFieldBegin(ID_RECORD_FIELD_DESC);
        oprot.writeString(struct.idRecord);
        oprot.writeFieldEnd();
      }
      if (struct.record2 != null) {
        oprot.writeFieldBegin(RECORD2_FIELD_DESC);
        oprot.writeString(struct.record2);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ROLLOVER_FIELD_DESC);
      oprot.writeBool(struct.rollover);
      oprot.writeFieldEnd();
      if (struct.toVkKcv != null) {
        oprot.writeFieldBegin(TO_VK_KCV_FIELD_DESC);
        oprot.writeString(struct.toVkKcv);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MeterConfigAdviceTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MeterConfigAdviceTupleScheme getScheme() {
      return new MeterConfigAdviceTupleScheme();
    }
  }

  private static class MeterConfigAdviceTupleScheme extends org.apache.thrift.scheme.TupleScheme<MeterConfigAdvice> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MeterConfigAdvice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI32(struct.toSgc);
      oprot.writeI16(struct.toKrn);
      oprot.writeI16(struct.toTi);
      oprot.writeI16(struct.toKen);
      oprot.writeString(struct.idRecord);
      oprot.writeString(struct.record2);
      oprot.writeBool(struct.rollover);
      oprot.writeString(struct.toVkKcv);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MeterConfigAdvice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.toSgc = iprot.readI32();
      struct.setToSgcIsSet(true);
      struct.toKrn = iprot.readI16();
      struct.setToKrnIsSet(true);
      struct.toTi = iprot.readI16();
      struct.setToTiIsSet(true);
      struct.toKen = iprot.readI16();
      struct.setToKenIsSet(true);
      struct.idRecord = iprot.readString();
      struct.setIdRecordIsSet(true);
      struct.record2 = iprot.readString();
      struct.setRecord2IsSet(true);
      struct.rollover = iprot.readBool();
      struct.setRolloverIsSet(true);
      struct.toVkKcv = iprot.readString();
      struct.setToVkKcvIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

