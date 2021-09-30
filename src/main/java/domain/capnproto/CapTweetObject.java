// Generated by Cap'n Proto compiler, DO NOT EDIT
// source: schema.capnp

package domain.capnproto;

public final class CapTweetObject {
    public static class TweetObject {
        public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)0,(short)4);
        public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
            public Factory() {
            }
            public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
                return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
            }
            public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
                return new Builder(segment, data, pointers, dataSize, pointerCount);
            }
            public final org.capnproto.StructSize structSize() {
                return TweetObject.STRUCT_SIZE;
            }
            public final Reader asReader(Builder builder) {
                return builder.asReader();
            }
        }
        public static final Factory factory = new Factory();
        public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
                new org.capnproto.StructList.Factory<Builder, Reader>(factory);
        public static final class Builder extends org.capnproto.StructBuilder {
            Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
                super(segment, data, pointers, dataSize, pointerCount);
            }
            public final Reader asReader() {
                return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
            }
            public final boolean hasCreatedAt() {
                return !_pointerFieldIsNull(0);
            }
            public final org.capnproto.Text.Builder getCreatedAt() {
                return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
            }
            public final void setCreatedAt(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 0, value);
            }
            public final void setCreatedAt(String value) {
                _setPointerField(org.capnproto.Text.factory, 0, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initCreatedAt(int size) {
                return _initPointerField(org.capnproto.Text.factory, 0, size);
            }
            public final boolean hasId() {
                return !_pointerFieldIsNull(1);
            }
            public final org.capnproto.Text.Builder getId() {
                return _getPointerField(org.capnproto.Text.factory, 1, null, 0, 0);
            }
            public final void setId(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 1, value);
            }
            public final void setId(String value) {
                _setPointerField(org.capnproto.Text.factory, 1, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initId(int size) {
                return _initPointerField(org.capnproto.Text.factory, 1, size);
            }
            public final boolean hasText() {
                return !_pointerFieldIsNull(2);
            }
            public final org.capnproto.Text.Builder getText() {
                return _getPointerField(org.capnproto.Text.factory, 2, null, 0, 0);
            }
            public final void setText(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 2, value);
            }
            public final void setText(String value) {
                _setPointerField(org.capnproto.Text.factory, 2, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initText(int size) {
                return _initPointerField(org.capnproto.Text.factory, 2, size);
            }
            public final domain.capnproto.CapTweetObject.User.Builder getUser() {
                return _getPointerField(domain.capnproto.CapTweetObject.User.factory, 3, null, 0);
            }
            public final void setUser(domain.capnproto.CapTweetObject.User.Reader value) {
                _setPointerField(domain.capnproto.CapTweetObject.User.factory,3, value);
            }
            public final domain.capnproto.CapTweetObject.User.Builder initUser() {
                return _initPointerField(domain.capnproto.CapTweetObject.User.factory,3, 0);
            }
        }

        public static final class Reader extends org.capnproto.StructReader {
            Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
                super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
            }

            public boolean hasCreatedAt() {
                return !_pointerFieldIsNull(0);
            }
            public org.capnproto.Text.Reader getCreatedAt() {
                return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
            }

            public boolean hasId() {
                return !_pointerFieldIsNull(1);
            }
            public org.capnproto.Text.Reader getId() {
                return _getPointerField(org.capnproto.Text.factory, 1, null, 0, 0);
            }

            public boolean hasText() {
                return !_pointerFieldIsNull(2);
            }
            public org.capnproto.Text.Reader getText() {
                return _getPointerField(org.capnproto.Text.factory, 2, null, 0, 0);
            }

            public boolean hasUser() {
                return !_pointerFieldIsNull(3);
            }
            public domain.capnproto.CapTweetObject.User.Reader getUser() {
                return _getPointerField(domain.capnproto.CapTweetObject.User.factory,3,null, 0);
            }

        }

    }


    public static class User {
        public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)1,(short)5);
        public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
            public Factory() {
            }
            public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
                return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
            }
            public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
                return new Builder(segment, data, pointers, dataSize, pointerCount);
            }
            public final org.capnproto.StructSize structSize() {
                return User.STRUCT_SIZE;
            }
            public final Reader asReader(Builder builder) {
                return builder.asReader();
            }
        }
        public static final Factory factory = new Factory();
        public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
                new org.capnproto.StructList.Factory<Builder, Reader>(factory);
        public static final class Builder extends org.capnproto.StructBuilder {
            Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
                super(segment, data, pointers, dataSize, pointerCount);
            }
            public final Reader asReader() {
                return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
            }
            public final boolean hasDescription() {
                return !_pointerFieldIsNull(0);
            }
            public final org.capnproto.Text.Builder getDescription() {
                return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
            }
            public final void setDescription(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 0, value);
            }
            public final void setDescription(String value) {
                _setPointerField(org.capnproto.Text.factory, 0, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initDescription(int size) {
                return _initPointerField(org.capnproto.Text.factory, 0, size);
            }
            public final int getId() {
                return _getIntField(0);
            }
            public final void setId(int value) {
                _setIntField(0, value);
            }

            public final domain.capnproto.CapTweetObject.Location.Builder getLocation() {
                return _getPointerField(domain.capnproto.CapTweetObject.Location.factory, 1, null, 0);
            }
            public final void setLocation(domain.capnproto.CapTweetObject.Location.Reader value) {
                _setPointerField(domain.capnproto.CapTweetObject.Location.factory,1, value);
            }
            public final domain.capnproto.CapTweetObject.Location.Builder initLocation() {
                return _initPointerField(domain.capnproto.CapTweetObject.Location.factory,1, 0);
            }
            public final boolean hasName() {
                return !_pointerFieldIsNull(2);
            }
            public final org.capnproto.Text.Builder getName() {
                return _getPointerField(org.capnproto.Text.factory, 2, null, 0, 0);
            }
            public final void setName(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 2, value);
            }
            public final void setName(String value) {
                _setPointerField(org.capnproto.Text.factory, 2, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initName(int size) {
                return _initPointerField(org.capnproto.Text.factory, 2, size);
            }
            public final boolean hasScreenName() {
                return !_pointerFieldIsNull(3);
            }
            public final org.capnproto.Text.Builder getScreenName() {
                return _getPointerField(org.capnproto.Text.factory, 3, null, 0, 0);
            }
            public final void setScreenName(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 3, value);
            }
            public final void setScreenName(String value) {
                _setPointerField(org.capnproto.Text.factory, 3, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initScreenName(int size) {
                return _initPointerField(org.capnproto.Text.factory, 3, size);
            }
            public final boolean hasUrl() {
                return !_pointerFieldIsNull(4);
            }
            public final org.capnproto.Text.Builder getUrl() {
                return _getPointerField(org.capnproto.Text.factory, 4, null, 0, 0);
            }
            public final void setUrl(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 4, value);
            }
            public final void setUrl(String value) {
                _setPointerField(org.capnproto.Text.factory, 4, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initUrl(int size) {
                return _initPointerField(org.capnproto.Text.factory, 4, size);
            }
        }

        public static final class Reader extends org.capnproto.StructReader {
            Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
                super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
            }

            public boolean hasDescription() {
                return !_pointerFieldIsNull(0);
            }
            public org.capnproto.Text.Reader getDescription() {
                return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
            }

            public final int getId() {
                return _getIntField(0);
            }

            public boolean hasLocation() {
                return !_pointerFieldIsNull(1);
            }
            public domain.capnproto.CapTweetObject.Location.Reader getLocation() {
                return _getPointerField(domain.capnproto.CapTweetObject.Location.factory,1,null, 0);
            }

            public boolean hasName() {
                return !_pointerFieldIsNull(2);
            }
            public org.capnproto.Text.Reader getName() {
                return _getPointerField(org.capnproto.Text.factory, 2, null, 0, 0);
            }

            public boolean hasScreenName() {
                return !_pointerFieldIsNull(3);
            }
            public org.capnproto.Text.Reader getScreenName() {
                return _getPointerField(org.capnproto.Text.factory, 3, null, 0, 0);
            }

            public boolean hasUrl() {
                return !_pointerFieldIsNull(4);
            }
            public org.capnproto.Text.Reader getUrl() {
                return _getPointerField(org.capnproto.Text.factory, 4, null, 0, 0);
            }

        }

    }


    public static class Location {
        public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)0,(short)2);
        public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
            public Factory() {
            }
            public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
                return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
            }
            public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
                return new Builder(segment, data, pointers, dataSize, pointerCount);
            }
            public final org.capnproto.StructSize structSize() {
                return Location.STRUCT_SIZE;
            }
            public final Reader asReader(Builder builder) {
                return builder.asReader();
            }
        }
        public static final Factory factory = new Factory();
        public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
                new org.capnproto.StructList.Factory<Builder, Reader>(factory);
        public static final class Builder extends org.capnproto.StructBuilder {
            Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
                super(segment, data, pointers, dataSize, pointerCount);
            }
            public final Reader asReader() {
                return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
            }
            public final boolean hasCity() {
                return !_pointerFieldIsNull(0);
            }
            public final org.capnproto.Text.Builder getCity() {
                return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
            }
            public final void setCity(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 0, value);
            }
            public final void setCity(String value) {
                _setPointerField(org.capnproto.Text.factory, 0, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initCity(int size) {
                return _initPointerField(org.capnproto.Text.factory, 0, size);
            }
            public final boolean hasCountry() {
                return !_pointerFieldIsNull(1);
            }
            public final org.capnproto.Text.Builder getCountry() {
                return _getPointerField(org.capnproto.Text.factory, 1, null, 0, 0);
            }
            public final void setCountry(org.capnproto.Text.Reader value) {
                _setPointerField(org.capnproto.Text.factory, 1, value);
            }
            public final void setCountry(String value) {
                _setPointerField(org.capnproto.Text.factory, 1, new org.capnproto.Text.Reader(value));
            }
            public final org.capnproto.Text.Builder initCountry(int size) {
                return _initPointerField(org.capnproto.Text.factory, 1, size);
            }
        }

        public static final class Reader extends org.capnproto.StructReader {
            Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
                super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
            }

            public boolean hasCity() {
                return !_pointerFieldIsNull(0);
            }
            public org.capnproto.Text.Reader getCity() {
                return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
            }

            public boolean hasCountry() {
                return !_pointerFieldIsNull(1);
            }
            public org.capnproto.Text.Reader getCountry() {
                return _getPointerField(org.capnproto.Text.factory, 1, null, 0, 0);
            }

        }

    }



    public static final class Schemas {
        public static final org.capnproto.SegmentReader b_ed4c17ada8da5f02 =
                org.capnproto.GeneratedClassSupport.decodeRawBytes(
                        "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
                                "\u0002\u005f\u00da\u00a8\u00ad\u0017\u004c\u00ed" +
                                "\r\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
                                "\u0074\u00e1\u006e\u00f8\u0019\u002e\u00b3\u009e" +
                                "\u0004\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0015\u0000\u0000\u0000\u00ca\u0000\u0000\u0000" +
                                "\u0021\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u001d\u0000\u0000\u0000\u00e7\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0073\u0063\u0068\u0065\u006d\u0061\u002e\u0063" +
                                "\u0061\u0070\u006e\u0070\u003a\u0054\u0077\u0065" +
                                "\u0065\u0074\u004f\u0062\u006a\u0065\u0063\u0074" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
                                "\u0010\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0061\u0000\u0000\u0000\u0052\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0060\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u006c\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0001\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0069\u0000\u0000\u0000\u001a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0064\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u0070\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0002\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0002\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u006d\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0068\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u0074\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0003\u0000\u0000\u0000\u0003\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0003\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0071\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u006c\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u0078\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0063\u0072\u0065\u0061\u0074\u0065\u0064\u0041" +
                                "\u0074\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0069\u0064\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0074\u0065\u0078\u0074\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0075\u0073\u0065\u0072\u0000\u0000\u0000\u0000" +
                                "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00a2\u0054\u00c3\u00f4\u00c2\u0089\u0083\u00a6" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
        public static final org.capnproto.SegmentReader b_a68389c2f4c354a2 =
                org.capnproto.GeneratedClassSupport.decodeRawBytes(
                        "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
                                "\u00a2\u0054\u00c3\u00f4\u00c2\u0089\u0083\u00a6" +
                                "\r\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
                                "\u0074\u00e1\u006e\u00f8\u0019\u002e\u00b3\u009e" +
                                "\u0005\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0015\u0000\u0000\u0000\u0092\u0000\u0000\u0000" +
                                "\u001d\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0019\u0000\u0000\u0000\u0057\u0001\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0073\u0063\u0068\u0065\u006d\u0061\u002e\u0063" +
                                "\u0061\u0070\u006e\u0070\u003a\u0055\u0073\u0065" +
                                "\u0072\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
                                "\u0018\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0099\u0000\u0000\u0000\u0062\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0098\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u00a4\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00a1\u0000\u0000\u0000\u001a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u009c\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u00a8\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0002\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0002\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00a5\u0000\u0000\u0000\u004a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00a4\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u00b0\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0003\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0003\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00ad\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00a8\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u00b4\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0004\u0000\u0000\u0000\u0003\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0004\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00b1\u0000\u0000\u0000\u005a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00b0\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u00bc\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0005\u0000\u0000\u0000\u0004\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0005\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00b9\u0000\u0000\u0000\"\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00b4\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u00c0\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0064\u0065\u0073\u0063\u0072\u0069\u0070\u0074" +
                                "\u0069\u006f\u006e\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0069\u0064\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0008\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0008\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u006c\u006f\u0063\u0061\u0074\u0069\u006f\u006e" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u00c9\u00b7\u00c5\u0005\u0040\u0055\u0019\u00b3" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u006e\u0061\u006d\u0065\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0073\u0063\u0072\u0065\u0065\u006e\u004e\u0061" +
                                "\u006d\u0065\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0075\u0072\u006c\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
        public static final org.capnproto.SegmentReader b_b319554005c5b7c9 =
                org.capnproto.GeneratedClassSupport.decodeRawBytes(
                        "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
                                "\u00c9\u00b7\u00c5\u0005\u0040\u0055\u0019\u00b3" +
                                "\r\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
                                "\u0074\u00e1\u006e\u00f8\u0019\u002e\u00b3\u009e" +
                                "\u0002\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0015\u0000\u0000\u0000\u00b2\u0000\u0000\u0000" +
                                "\u001d\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0019\u0000\u0000\u0000\u0077\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0073\u0063\u0068\u0065\u006d\u0061\u002e\u0063" +
                                "\u0061\u0070\u006e\u0070\u003a\u004c\u006f\u0063" +
                                "\u0061\u0074\u0069\u006f\u006e\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
                                "\u0008\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0029\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0024\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u0030\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0001\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
                                "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u002d\u0000\u0000\u0000\u0042\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0028\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
                                "\u0034\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
                                "\u0063\u0069\u0074\u0079\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0063\u006f\u0075\u006e\u0074\u0072\u0079\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
    }
}
