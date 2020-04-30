/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package domain.avro;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class TweetObject extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1710634909141526949L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TweetObject\",\"namespace\":\"domain.avro\",\"fields\":[{\"name\":\"createdAt\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"idStr\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"text\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"user\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"userRecord\",\"fields\":[{\"name\":\"description\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"id\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"location\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"location\",\"fields\":[{\"name\":\"city\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"country\",\"type\":[\"null\",\"string\"],\"default\":null}]}],\"default\":null},{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"screenName\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"url\",\"type\":[\"null\",\"string\"],\"default\":null}]}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TweetObject> ENCODER =
      new BinaryMessageEncoder<TweetObject>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TweetObject> DECODER =
      new BinaryMessageDecoder<TweetObject>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<TweetObject> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<TweetObject> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<TweetObject> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TweetObject>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this TweetObject to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a TweetObject from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a TweetObject instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static TweetObject fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence createdAt;
  @Deprecated public java.lang.CharSequence idStr;
  @Deprecated public java.lang.CharSequence text;
  @Deprecated public domain.avro.userRecord user;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TweetObject() {}

  /**
   * All-args constructor.
   * @param createdAt The new value for createdAt
   * @param idStr The new value for idStr
   * @param text The new value for text
   * @param user The new value for user
   */
  public TweetObject(java.lang.CharSequence createdAt, java.lang.CharSequence idStr, java.lang.CharSequence text, domain.avro.userRecord user) {
    this.createdAt = createdAt;
    this.idStr = idStr;
    this.text = text;
    this.user = user;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return createdAt;
    case 1: return idStr;
    case 2: return text;
    case 3: return user;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: createdAt = (java.lang.CharSequence)value$; break;
    case 1: idStr = (java.lang.CharSequence)value$; break;
    case 2: text = (java.lang.CharSequence)value$; break;
    case 3: user = (domain.avro.userRecord)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.lang.CharSequence getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.lang.CharSequence value) {
    this.createdAt = value;
  }

  /**
   * Gets the value of the 'idStr' field.
   * @return The value of the 'idStr' field.
   */
  public java.lang.CharSequence getIdStr() {
    return idStr;
  }


  /**
   * Sets the value of the 'idStr' field.
   * @param value the value to set.
   */
  public void setIdStr(java.lang.CharSequence value) {
    this.idStr = value;
  }

  /**
   * Gets the value of the 'text' field.
   * @return The value of the 'text' field.
   */
  public java.lang.CharSequence getText() {
    return text;
  }


  /**
   * Sets the value of the 'text' field.
   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
  }

  /**
   * Gets the value of the 'user' field.
   * @return The value of the 'user' field.
   */
  public domain.avro.userRecord getUser() {
    return user;
  }


  /**
   * Sets the value of the 'user' field.
   * @param value the value to set.
   */
  public void setUser(domain.avro.userRecord value) {
    this.user = value;
  }

  /**
   * Creates a new TweetObject RecordBuilder.
   * @return A new TweetObject RecordBuilder
   */
  public static domain.avro.TweetObject.Builder newBuilder() {
    return new domain.avro.TweetObject.Builder();
  }

  /**
   * Creates a new TweetObject RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TweetObject RecordBuilder
   */
  public static domain.avro.TweetObject.Builder newBuilder(domain.avro.TweetObject.Builder other) {
    if (other == null) {
      return new domain.avro.TweetObject.Builder();
    } else {
      return new domain.avro.TweetObject.Builder(other);
    }
  }

  /**
   * Creates a new TweetObject RecordBuilder by copying an existing TweetObject instance.
   * @param other The existing instance to copy.
   * @return A new TweetObject RecordBuilder
   */
  public static domain.avro.TweetObject.Builder newBuilder(domain.avro.TweetObject other) {
    if (other == null) {
      return new domain.avro.TweetObject.Builder();
    } else {
      return new domain.avro.TweetObject.Builder(other);
    }
  }

  /**
   * RecordBuilder for TweetObject instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TweetObject>
    implements org.apache.avro.data.RecordBuilder<TweetObject> {

    private java.lang.CharSequence createdAt;
    private java.lang.CharSequence idStr;
    private java.lang.CharSequence text;
    private domain.avro.userRecord user;
    private domain.avro.userRecord.Builder userBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(domain.avro.TweetObject.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[0].schema(), other.createdAt);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.idStr)) {
        this.idStr = data().deepCopy(fields()[1].schema(), other.idStr);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.text)) {
        this.text = data().deepCopy(fields()[2].schema(), other.text);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.user)) {
        this.user = data().deepCopy(fields()[3].schema(), other.user);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (other.hasUserBuilder()) {
        this.userBuilder = domain.avro.userRecord.newBuilder(other.getUserBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing TweetObject instance
     * @param other The existing instance to copy.
     */
    private Builder(domain.avro.TweetObject other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[0].schema(), other.createdAt);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.idStr)) {
        this.idStr = data().deepCopy(fields()[1].schema(), other.idStr);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.text)) {
        this.text = data().deepCopy(fields()[2].schema(), other.text);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.user)) {
        this.user = data().deepCopy(fields()[3].schema(), other.user);
        fieldSetFlags()[3] = true;
      }
      this.userBuilder = null;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.lang.CharSequence getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder setCreatedAt(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.createdAt = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder clearCreatedAt() {
      createdAt = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'idStr' field.
      * @return The value.
      */
    public java.lang.CharSequence getIdStr() {
      return idStr;
    }


    /**
      * Sets the value of the 'idStr' field.
      * @param value The value of 'idStr'.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder setIdStr(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.idStr = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'idStr' field has been set.
      * @return True if the 'idStr' field has been set, false otherwise.
      */
    public boolean hasIdStr() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'idStr' field.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder clearIdStr() {
      idStr = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'text' field.
      * @return The value.
      */
    public java.lang.CharSequence getText() {
      return text;
    }


    /**
      * Sets the value of the 'text' field.
      * @param value The value of 'text'.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder setText(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.text = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'text' field has been set.
      * @return True if the 'text' field has been set, false otherwise.
      */
    public boolean hasText() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'text' field.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder clearText() {
      text = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'user' field.
      * @return The value.
      */
    public domain.avro.userRecord getUser() {
      return user;
    }


    /**
      * Sets the value of the 'user' field.
      * @param value The value of 'user'.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder setUser(domain.avro.userRecord value) {
      validate(fields()[3], value);
      this.userBuilder = null;
      this.user = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'user' field has been set.
      * @return True if the 'user' field has been set, false otherwise.
      */
    public boolean hasUser() {
      return fieldSetFlags()[3];
    }

    /**
     * Gets the Builder instance for the 'user' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public domain.avro.userRecord.Builder getUserBuilder() {
      if (userBuilder == null) {
        if (hasUser()) {
          setUserBuilder(domain.avro.userRecord.newBuilder(user));
        } else {
          setUserBuilder(domain.avro.userRecord.newBuilder());
        }
      }
      return userBuilder;
    }

    /**
     * Sets the Builder instance for the 'user' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public domain.avro.TweetObject.Builder setUserBuilder(domain.avro.userRecord.Builder value) {
      clearUser();
      userBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'user' field has an active Builder instance
     * @return True if the 'user' field has an active Builder instance
     */
    public boolean hasUserBuilder() {
      return userBuilder != null;
    }

    /**
      * Clears the value of the 'user' field.
      * @return This builder.
      */
    public domain.avro.TweetObject.Builder clearUser() {
      user = null;
      userBuilder = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TweetObject build() {
      try {
        TweetObject record = new TweetObject();
        record.createdAt = fieldSetFlags()[0] ? this.createdAt : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.idStr = fieldSetFlags()[1] ? this.idStr : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.text = fieldSetFlags()[2] ? this.text : (java.lang.CharSequence) defaultValue(fields()[2]);
        if (userBuilder != null) {
          try {
            record.user = this.userBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("user"));
            throw e;
          }
        } else {
          record.user = fieldSetFlags()[3] ? this.user : (domain.avro.userRecord) defaultValue(fields()[3]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TweetObject>
    WRITER$ = (org.apache.avro.io.DatumWriter<TweetObject>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TweetObject>
    READER$ = (org.apache.avro.io.DatumReader<TweetObject>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.createdAt == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.createdAt);
    }

    if (this.idStr == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.idStr);
    }

    if (this.text == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.text);
    }

    if (this.user == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      this.user.customEncode(out);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.createdAt = null;
      } else {
        this.createdAt = in.readString(this.createdAt instanceof Utf8 ? (Utf8)this.createdAt : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.idStr = null;
      } else {
        this.idStr = in.readString(this.idStr instanceof Utf8 ? (Utf8)this.idStr : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.text = null;
      } else {
        this.text = in.readString(this.text instanceof Utf8 ? (Utf8)this.text : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.user = null;
      } else {
        if (this.user == null) {
          this.user = new domain.avro.userRecord();
        }
        this.user.customDecode(in);
      }

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.createdAt = null;
          } else {
            this.createdAt = in.readString(this.createdAt instanceof Utf8 ? (Utf8)this.createdAt : null);
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.idStr = null;
          } else {
            this.idStr = in.readString(this.idStr instanceof Utf8 ? (Utf8)this.idStr : null);
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.text = null;
          } else {
            this.text = in.readString(this.text instanceof Utf8 ? (Utf8)this.text : null);
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.user = null;
          } else {
            if (this.user == null) {
              this.user = new domain.avro.userRecord();
            }
            this.user.customDecode(in);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









