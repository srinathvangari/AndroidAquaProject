//import 'dart:js';

import 'package:customerapp/BookVacation.dart';
import 'package:customerapp/ContactUs.dart';
import 'package:customerapp/DeliveryReport.dart';
import 'package:customerapp/BillDownload.dart';
import 'package:customerapp/Language.dart';
import 'package:customerapp/LegalPolicies.dart';
import 'package:customerapp/LoginPage.dart';
//import 'package:customerapp/LoginPage.dart';
import 'package:customerapp/MyAccount.dart';
import 'package:customerapp/Payment.dart';
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
//import 'package:cupertino_icons/cupertino_icons.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

void main() {
  runApp(
    EasyLocalization(
      supportedLocales: [
        Locale('en', 'US'),
        Locale('hi', 'IN'),
        Locale('mr', 'IN')
      ],
      path: 'assets/languages',
      fallbackLocale: Locale('en', 'US'),
      child: MyApp(),
    ),
  );
}

// Dio dio = new Dio();

// Future postData() async{
//   final String pathUrl = 'http://13.232.142.88:8080/dailydairyRest/login';

//   dynamic data = {
//     'title': 'Flutter Http post',
//     'body' : 'Flutter is Awesome',
//     'userID' : 1
//   };

//   var responce = await dio.post(pathUrl,data: data,options: options(
//     Headers: {
//       'Content-Type': 'application/json; charset=UTF-8',
//     }
//   ));

//   return responce.data;
// }

// curl -X POST "http://13.232.142.88:8080/dailydairyRest/login"
// -H "accept: /"
// -H "Content-Type: application/json"
// -d "{ \"loginId\": \"7875893838\", \"pwd\": \"test\"}"

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      supportedLocales: context.supportedLocales,
      localizationsDelegates: context.localizationDelegates,
      title: 'Customer App',
      theme: ThemeData(
        primarySwatch: Colors.indigo,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Home(),
      //  routes: {
      // "/logon": (context) => LoginPage(),
      // "/home": (context) => HomePage()
      // },
    );
  }
}

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final data = MediaQuery.of(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('Dashboard').tr(),
      ),
      body: SafeArea(
        child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Container(
                height: 150.0,
                decoration: BoxDecoration(
                    image: DecorationImage(
                        image: AssetImage('assets/images/background.png'),
                        fit: BoxFit.fill)),
              ),

              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Column(
                  children: <Widget>[
                    Container(
                        height: 64,
                        child: Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Padding(
                              padding: const EdgeInsets.all(5.0),
                              child: CircleAvatar(
                                radius: 55.0,
                                backgroundImage:
                                    AssetImage('assets/images/dairy_icon.png'),
                              ),
                            ),
                            SizedBox(
                              width: 15.0,
                            ),
                            Column(
                                mainAxisAlignment: MainAxisAlignment.center,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text('Khalsa Dairy',
                                      style: TextStyle(
                                        fontFamily: 'The',
                                        fontWeight: FontWeight.bold,
                                        fontSize: 22,
                                        color: Colors.black,
                                      )),
                                  Text('ABC Area, Pune- 411028')
                                ])
                          ],
                        ))
                  ],
                ),
              ),
/////////////////////////////////////////////////////////////////////////////////
              SizedBox(
                height: 20.0,
              ),

              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  new SizedBox(
                    width: data.size.width / 2.5,
                    height: data.size.height / 5,
                    child: RaisedButton(
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => DeliveryReport()));
                      },
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Column(
                          children: [
                            SizedBox(height: 20.0),
                            Icon(FontAwesomeIcons.file, size: 25),
                            SizedBox(height: 20.0),
                            Text(
                              'Delivery Report',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                  fontSize: 15,
                                  color: Colors.indigo,
                                  fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      ),
                      //.tr(),
                      color: Colors.white,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10.0),
                          side: BorderSide(color: Colors.white)),
                    ),
                  ),
                  new SizedBox(
                    width: data.size.width / 2.5,
                    height: data.size.height / 5,
                    child: RaisedButton(
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => BillDowmload()));
                      },
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Column(
                          children: [
                            SizedBox(height: 20.0),
                            Icon(FontAwesomeIcons.fileDownload, size: 25),
                            SizedBox(height: 20.0),
                            Text(
                              'Bill Download',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                  fontSize: 15,
                                  color: Colors.indigo,
                                  fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      ),
                      //.tr(),
                      color: Colors.white,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10.0),
                          side: BorderSide(color: Colors.white)),
                      //textColor: Colors.white,
                    ),
                  ),
                ],
              ),

              SizedBox(
                height: 20.0,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  new SizedBox(
                    width: data.size.width / 2.5,
                    height: data.size.height / 5,
                    child: RaisedButton(
                      onPressed: () {
                        Navigator.push(context,
                            MaterialPageRoute(builder: (context) => Payment()));
                      },
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Column(
                          children: [
                            SizedBox(height: 20.0),
                            Icon(FontAwesomeIcons.moneyBillAlt, size: 25),
                            SizedBox(height: 20.0),
                            Text(
                              'Payment',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                  fontSize: 15,
                                  color: Colors.indigo,
                                  fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      ),
                      //.tr(),
                      color: Colors.white,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10.0),
                          side: BorderSide(color: Colors.white)),
                    ),
                  ),
                  new SizedBox(
                    width: data.size.width / 2.5,
                    height: data.size.height / 5,
                    child: RaisedButton(
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => BookVacation()));
                      },
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Column(
                          children: [
                            SizedBox(height: 20.0),
                            Icon(FontAwesomeIcons.umbrella, size: 25),
                            SizedBox(height: 20.0),
                            Text(
                              'Book Vacations',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                  fontSize: 15,
                                  color: Colors.indigo,
                                  fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      ),
                      //.tr(),
                      color: Colors.white,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10.0),
                          side: BorderSide(color: Colors.white)),
                      //textColor: Colors.white,
                    ),
                  ),
                ],
              ),

//////////////////////////////////////////////////////////////////////////////////////////////
            ]),
      ),
      drawer: Drawer(
          child: ListView(
        children: <Widget>[
          DrawerHeader(
            decoration: BoxDecoration(color: Colors.indigo),
            child: Padding(
              padding: EdgeInsets.all(10),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Column(
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.all(2.0),
                        child: Container(
                          width: 75.0,
                          height: 75.0,
                          child: CircleAvatar(
                            backgroundImage:
                                AssetImage('assets/images/user.png'),
                          ),
                        ),
                      ),
                      //SizedBox(height: 2.0),

                      Text('Alex Buton'),
                      Text('+919876543210'),
                    ],
                  ),
                ],
              ),
            ),
          ),
          ListTile(
            leading: Icon(Icons.person),
            title: Text('My Account').tr(),
            subtitle: Text('Profile').tr(),
            trailing: Icon(Icons.edit),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => MyAccount()));
              //Navigator.of(context).pushNamed(MyAccount.routeName);
            },
          ),
          ListTile(
            leading: Icon(Icons.message),
            title: Text('Message'),
            //.tr(),
            onTap: () {
              // Navigator.push(context,
              //     MaterialPageRoute(builder: (context) => MyAccount()));
              //Navigator.of(context).pushNamed(MyAccount.routeName);
            },
          ),
          ListTile(
            leading: Icon(Icons.language_sharp),
            title: Text('Change Language'),
            //.tr(),
            onTap: () {
              Navigator.push(
                  context, MaterialPageRoute(builder: (context) => Language()));
              //Navigator.of(context).pushNamed(MyAccount.routeName);
            },
          ),
          ListTile(
            leading: Icon(Icons.contact_support),
            title: Text('Contact Us'),
            //.tr(),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => ContactUs()));
            },
          ),
          ListTile(
            leading: Icon(Icons.policy_outlined),
            title: Text('Legal Policies'),
            //.tr(),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => LegalPolicies()));
            },
          ),
          ListTile(
            leading: Icon(Icons.logout),
            title: Text('Logout'),
            //.tr(),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => LoginPage()));
            },
          ),
        ],
      )),
    );
  }
}
