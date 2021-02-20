import 'package:flutter/material.dart';
import 'bg_image.dart';
import 'package:easy_localization/easy_localization.dart';
import 'main.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final userNameController = TextEditingController();

  final passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Milk Dairy Customer"),
      ),
      body: Stack(fit: StackFit.expand, children: <Widget>[
        bg_image(),
        Center(
          child: Padding(
              padding: const EdgeInsets.all(15.0),
              child: SingleChildScrollView(
                child: Column(children: <Widget>[
                  Card(
                    child: Center(
                      child: Form(
                          child: Padding(
                        padding: const EdgeInsets.all(15.0),
                        child: Column(children: <Widget>[
                          TextFormField(
                            decoration: InputDecoration(
                                hintText: "Enter username",
                                labelText: "username"),
                          ),
                          SizedBox(
                            height: 20,
                          ),
                          TextFormField(
                            obscureText: true,
                            decoration: InputDecoration(
                                hintText: "Enter password",
                                labelText: "password"),
                          ),
                          SizedBox(
                            height: 20,
                          ),
                          RaisedButton(
                            onPressed: () {
                              // async {
                              //   print('POSTING DATA------------->');
                              //   await postData().then((value){
                              //     print(value);
                              //   });
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => MyApp()));
                            },
                            child: Text('Sign In').tr(),
                            color: Colors.white,
                            textColor: Colors.black,
                          ),
                        ]),
                      )),
                    ),
                  )
                ]),
              )),
        )
      ]),
    );
  }
}
