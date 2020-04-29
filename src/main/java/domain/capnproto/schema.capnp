using Java = import "./java.capnp";
$Java.package("domain.capnproto");
$Java.outerClassname("CapTweetObject");

struct Person {
  createdAt @0 :Text;
  id @1 :Text;
  text @2 :Text;
  user @3 :User;
}

struct User {
  description @0: Text;
  id @1 :UInt32;
  location @2: Location;
  name @3 : Text;
  screenName @4 : Text;
  url @5 : Text;
}

struct Location {
  city @0 : Text;
  country @1 :Text;
}