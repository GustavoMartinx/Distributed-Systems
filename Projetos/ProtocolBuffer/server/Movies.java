// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Movies.proto

public final class Movies {
  private Movies() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MovieOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Movie)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string title = 1;</code>
     * @return The title.
     */
    java.lang.String getTitle();
    /**
     * <code>string title = 1;</code>
     * @return The bytes for title.
     */
    com.google.protobuf.ByteString
        getTitleBytes();

    /**
     * <code>int32 id = 2;</code>
     * @return The id.
     */
    int getId();

    /**
     * <code>string directors = 3;</code>
     * @return The directors.
     */
    java.lang.String getDirectors();
    /**
     * <code>string directors = 3;</code>
     * @return The bytes for directors.
     */
    com.google.protobuf.ByteString
        getDirectorsBytes();
  }
  /**
   * Protobuf type {@code Movie}
   */
  public static final class Movie extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Movie)
      MovieOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Movie.newBuilder() to construct.
    private Movie(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Movie() {
      title_ = "";
      directors_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Movie();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Movies.internal_static_Movie_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Movies.internal_static_Movie_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Movies.Movie.class, Movies.Movie.Builder.class);
    }

    public static final int TITLE_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object title_ = "";
    /**
     * <code>string title = 1;</code>
     * @return The title.
     */
    @java.lang.Override
    public java.lang.String getTitle() {
      java.lang.Object ref = title_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        title_ = s;
        return s;
      }
    }
    /**
     * <code>string title = 1;</code>
     * @return The bytes for title.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getTitleBytes() {
      java.lang.Object ref = title_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        title_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ID_FIELD_NUMBER = 2;
    private int id_ = 0;
    /**
     * <code>int32 id = 2;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }

    public static final int DIRECTORS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object directors_ = "";
    /**
     * <code>string directors = 3;</code>
     * @return The directors.
     */
    @java.lang.Override
    public java.lang.String getDirectors() {
      java.lang.Object ref = directors_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        directors_ = s;
        return s;
      }
    }
    /**
     * <code>string directors = 3;</code>
     * @return The bytes for directors.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getDirectorsBytes() {
      java.lang.Object ref = directors_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        directors_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(title_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, title_);
      }
      if (id_ != 0) {
        output.writeInt32(2, id_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(directors_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, directors_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(title_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, title_);
      }
      if (id_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, id_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(directors_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, directors_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof Movies.Movie)) {
        return super.equals(obj);
      }
      Movies.Movie other = (Movies.Movie) obj;

      if (!getTitle()
          .equals(other.getTitle())) return false;
      if (getId()
          != other.getId()) return false;
      if (!getDirectors()
          .equals(other.getDirectors())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TITLE_FIELD_NUMBER;
      hash = (53 * hash) + getTitle().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
      hash = (37 * hash) + DIRECTORS_FIELD_NUMBER;
      hash = (53 * hash) + getDirectors().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static Movies.Movie parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Movies.Movie parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Movies.Movie parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Movies.Movie parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Movies.Movie parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Movies.Movie parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Movies.Movie parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static Movies.Movie parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Movies.Movie parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static Movies.Movie parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static Movies.Movie parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static Movies.Movie parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(Movies.Movie prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Movie}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Movie)
        Movies.MovieOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Movies.internal_static_Movie_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Movies.internal_static_Movie_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Movies.Movie.class, Movies.Movie.Builder.class);
      }

      // Construct using Movies.Movie.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        title_ = "";
        id_ = 0;
        directors_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Movies.internal_static_Movie_descriptor;
      }

      @java.lang.Override
      public Movies.Movie getDefaultInstanceForType() {
        return Movies.Movie.getDefaultInstance();
      }

      @java.lang.Override
      public Movies.Movie build() {
        Movies.Movie result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public Movies.Movie buildPartial() {
        Movies.Movie result = new Movies.Movie(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(Movies.Movie result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.title_ = title_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.id_ = id_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.directors_ = directors_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Movies.Movie) {
          return mergeFrom((Movies.Movie)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Movies.Movie other) {
        if (other == Movies.Movie.getDefaultInstance()) return this;
        if (!other.getTitle().isEmpty()) {
          title_ = other.title_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (!other.getDirectors().isEmpty()) {
          directors_ = other.directors_;
          bitField0_ |= 0x00000004;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                title_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 16: {
                id_ = input.readInt32();
                bitField0_ |= 0x00000002;
                break;
              } // case 16
              case 26: {
                directors_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000004;
                break;
              } // case 26
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object title_ = "";
      /**
       * <code>string title = 1;</code>
       * @return The title.
       */
      public java.lang.String getTitle() {
        java.lang.Object ref = title_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          title_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string title = 1;</code>
       * @return The bytes for title.
       */
      public com.google.protobuf.ByteString
          getTitleBytes() {
        java.lang.Object ref = title_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          title_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string title = 1;</code>
       * @param value The title to set.
       * @return This builder for chaining.
       */
      public Builder setTitle(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        title_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>string title = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearTitle() {
        title_ = getDefaultInstance().getTitle();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>string title = 1;</code>
       * @param value The bytes for title to set.
       * @return This builder for chaining.
       */
      public Builder setTitleBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        title_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private int id_ ;
      /**
       * <code>int32 id = 2;</code>
       * @return The id.
       */
      @java.lang.Override
      public int getId() {
        return id_;
      }
      /**
       * <code>int32 id = 2;</code>
       * @param value The id to set.
       * @return This builder for chaining.
       */
      public Builder setId(int value) {

        id_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>int32 id = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        id_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object directors_ = "";
      /**
       * <code>string directors = 3;</code>
       * @return The directors.
       */
      public java.lang.String getDirectors() {
        java.lang.Object ref = directors_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          directors_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string directors = 3;</code>
       * @return The bytes for directors.
       */
      public com.google.protobuf.ByteString
          getDirectorsBytes() {
        java.lang.Object ref = directors_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          directors_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string directors = 3;</code>
       * @param value The directors to set.
       * @return This builder for chaining.
       */
      public Builder setDirectors(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        directors_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <code>string directors = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearDirectors() {
        directors_ = getDefaultInstance().getDirectors();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }
      /**
       * <code>string directors = 3;</code>
       * @param value The bytes for directors to set.
       * @return This builder for chaining.
       */
      public Builder setDirectorsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        directors_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Movie)
    }

    // @@protoc_insertion_point(class_scope:Movie)
    private static final Movies.Movie DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new Movies.Movie();
    }

    public static Movies.Movie getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Movie>
        PARSER = new com.google.protobuf.AbstractParser<Movie>() {
      @java.lang.Override
      public Movie parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Movie> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Movie> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public Movies.Movie getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Movie_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Movie_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Movies.proto\"5\n\005Movie\022\r\n\005title\030\001 \001(\t\022\n" +
      "\n\002id\030\002 \001(\005\022\021\n\tdirectors\030\003 \001(\tb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Movie_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Movie_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Movie_descriptor,
        new java.lang.String[] { "Title", "Id", "Directors", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}