# About the Mock Framework

_acme4j_ is designed to make access to ACME servers as easy and transparent as possible. The API is designed to feel straightforward. You won't even know when the ACME server is actually accessed. This makes writing an ACME client rather easy, but writing unit tests for it is hell.

The `acme4j-mock` module offers a unit testing framework that is supposed to make unit testing a lot easier. All you need to do is add it as `test` scoped dependency to your application.

## How it works

Essentially, the ACME protocol exchanges signed JSON data via HTTP. On the _acme4j_ side, each ACME request is passed to the `org.shredzone.acme4j.connector.Connection` interface. The implementation takes care for signing and sending the request, parsing the response, and do some other necessary things.

This is where the mock framework comes in. It provides an own `Connection` implementation, which "connects" to a mock ACME server instead of a real server. All the communication is done internally, without actual HTTP requests being sent to a real server. This is exactly what we would want for unit tests. They run in an environment that is closed in itself, and do not depend on the presence of external resources (like a network connection).

Also, the unit test has full control over the mock ACME server. The test can prepare the server as necessary, and it can also simulate errors and rare corner cases.

## Caveats

The mock server is just this: a mock-up. It's not a fully-blown ACME server, far from it. This is not the purpose of this framework.

For this reason, there are a few caveats to remember when writing unit tests using the `acme4j-mock` framework.

* The mock server is a very simple implementation. It does not perform all the elaborate validation steps of a real ACME server. It also does behave differently than a real ACME server in many situations. It is not meant for integration tests, but just for simple unit tests.
* Since no real HTTP requests are taking place, all the URLs involved are plain virtual, and cannot be accessed (e.g. by a HTTP client).
* It also means that there is no actual domain validation taking place. You won't need to run a HTTP or DNS server. If a challenge shall be deemed successful, just change its status at the ACME server accordingly.
* The URLs are purely random. Do not assume a fixed structure of the URLs. Every URL may look completely different on the next run of the unit test, or the next _acme4j_ version.
* You cannot access the mock server by an `acme` URI. To access it, you must always use the `Session` that is generated by the mock server (see next chapter).
* The mock framework is not thread-safe.
* It is not meant to be a replacement for integration tests. It is still recommended to run full integration tests with your code (e.g. against a [Pebble](https://github.com/letsencrypt/pebble) server).

## Experimental!

The mock framework is still experimental. As feedback comes in, there will surely be some parts that need improvement. Expect breaking API changes in the next couple of _acme4j_ versions.