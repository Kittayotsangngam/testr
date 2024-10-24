import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const platform = MethodChannel('com.example/bloodGlucose');

  // Example function to start blood glucose monitoring
  Future<void> startBloodGlucoseMonitor() async {
    try {
      await platform.invokeMethod('startBloodGlucoseMonitor');
    } on PlatformException catch (e) {
      print("Failed to start blood glucose monitor: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Blood Glucose Monitoring")),
      body: Center(
        child: ElevatedButton(
          onPressed: startBloodGlucoseMonitor,
          child: Text('Start Monitoring'),
        ),
      ),
    );
  }
}
