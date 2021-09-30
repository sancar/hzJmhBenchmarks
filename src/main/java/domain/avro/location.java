/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package domain.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class location extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 313005995929411535L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"location\",\"namespace\":\"domain.avro\",\"fields\":[{\"name\":\"city\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"country\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<location> ENCODER =
      new BinaryMessageEncoder<location>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<location> DECODER =
      new BinaryMessageDecoder<location>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<location> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<location> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<location> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<location>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this location to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a location from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a location instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static location fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence city;
  @Deprecated public java.lang.CharSequence country;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public location() {}

  /**
   * All-args constructor.
   * @param city The new value for city
   * @param country The new value for country
   */
  public location(java.lang.CharSequence city, java.lang.CharSequence country) {
    this.city = city;
    this.country = country;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return city;
    case 1: return country;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: city = (java.lang.CharSequence)value$; break;
    case 1: country = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'city' field.
   * @return The value of the 'city' field.
   */
  public java.lang.CharSequence getCity() {
    return city;
  }


  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return The value of the 'country' field.
   */
  public java.lang.CharSequence getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(java.lang.CharSequence value) {
    this.country = value;
  }

  /**
   * Creates a new location RecordBuilder.
   * @return A new location RecordBuilder
   */
  public static domain.avro.location.Builder newBuilder() {
    return new domain.avro.location.Builder();
  }

  /**
   * Creates a new location RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new location RecordBuilder
   */
  public static domain.avro.location.Builder newBuilder(domain.avro.location.Builder other) {
    if (other == null) {
      return new domain.avro.location.Builder();
    } else {
      return new domain.avro.location.Builder(other);
    }
  }

  /**
   * Creates a new location RecordBuilder by copying an existing location instance.
   * @param other The existing instance to copy.
   * @return A new location RecordBuilder
   */
  public static domain.avro.location.Builder newBuilder(domain.avro.location other) {
    if (other == null) {
      return new domain.avro.location.Builder();
    } else {
      return new domain.avro.location.Builder(other);
    }
  }

  /**
   * RecordBuilder for location instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<location>
    implements org.apache.avro.data.RecordBuilder<location> {

    private java.lang.CharSequence city;
    private java.lang.CharSequence country;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(domain.avro.location.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.city)) {
        this.city = data().deepCopy(fields()[0].schema(), other.city);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.country)) {
        this.country = data().deepCopy(fields()[1].schema(), other.country);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing location instance
     * @param other The existing instance to copy.
     */
    private Builder(domain.avro.location other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.city)) {
        this.city = data().deepCopy(fields()[0].schema(), other.city);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.country)) {
        this.country = data().deepCopy(fields()[1].schema(), other.country);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'city' field.
      * @return The value.
      */
    public java.lang.CharSequence getCity() {
      return city;
    }


    /**
      * Sets the value of the 'city' field.
      * @param value The value of 'city'.
      * @return This builder.
      */
    public domain.avro.location.Builder setCity(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.city = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'city' field.
      * @return This builder.
      */
    public domain.avro.location.Builder clearCity() {
      city = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * @return The value.
      */
    public java.lang.CharSequence getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * @param value The value of 'country'.
      * @return This builder.
      */
    public domain.avro.location.Builder setCountry(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.country = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'country' field.
      * @return This builder.
      */
    public domain.avro.location.Builder clearCountry() {
      country = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public location build() {
      try {
        location record = new location();
        record.city = fieldSetFlags()[0] ? this.city : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.country = fieldSetFlags()[1] ? this.country : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<location>
    WRITER$ = (org.apache.avro.io.DatumWriter<location>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<location>
    READER$ = (org.apache.avro.io.DatumReader<location>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.city == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.city);
    }

    if (this.country == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.country);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.city = null;
      } else {
        this.city = in.readString(this.city instanceof Utf8 ? (Utf8)this.city : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.country = null;
      } else {
        this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
      }

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.city = null;
          } else {
            this.city = in.readString(this.city instanceof Utf8 ? (Utf8)this.city : null);
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.country = null;
          } else {
            this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









