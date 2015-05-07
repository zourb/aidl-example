# AIDL Example

This is a basic example of the Android Interface Definition Language, a component of the Android framework
that allows to separate apps (processes) to communicate with each other using a "contract" (interface).

![Screenshot](https://raw.githubusercontent.com/afollestad/aidl-example/master/art/screenshot1.png)

### Using this example

To observe this example project work, you must first install the `receiver` module on your device
(it doesn't show any UI, it's just a `Service`). Once it's installed, install and run the `app` module
on your device. The `app` module will display UI that starts the service, binds with the service,
and uses methods declared in the service.

Note it also tells you how long it took to receive the entire response from the Service, AIDL is *very* fast
compared to other forms of IPC.