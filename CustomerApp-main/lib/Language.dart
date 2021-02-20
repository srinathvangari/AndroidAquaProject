//import 'package:customerapp/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:easy_localization/easy_localization.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

class Language extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Go Home').tr(),
      ),

      body: Padding(
        padding: const EdgeInsets.all(25.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(
              height: 20.0,
            ),
            Row(
              children: [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Icon(FontAwesomeIcons.speakap, size: 25),
                ),
                SizedBox(height: 50.0, width: 15.0),
                Text("Select Language:",
                    style: TextStyle(
                      fontFamily: 'The',
                      fontWeight: FontWeight.bold,
                      fontSize: 22,
                      color: Colors.black,
                    )),
              ],
            ),
            SizedBox(
              height: 15.0,
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: RaisedButton(
                onPressed: () {
                  context.locale = Locale('en', 'US');
                },
                child: Text('Change to English'),
                color: Colors.white,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0),
                    side: BorderSide(color: Colors.white)),
              ),
            ),
              SizedBox(
              height: 5.0,
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: RaisedButton(
                onPressed: () {
                  context.locale = Locale('hi', 'IN');
                },
                child: Text('Change to Hindi'),
                color: Colors.white,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0),
                    side: BorderSide(color: Colors.white)),
              ),
            ),
              SizedBox(
              height: 5.0,
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: RaisedButton(
                onPressed: () {
                  context.locale = Locale('mr', 'IN');
                },
                child: Text('Change to Marathi'),
                color: Colors.white,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0),
                    side: BorderSide(color: Colors.white)),
              ),
            ),
          ],
        ),
      ),

      // body: Center(
      //   child: Column(
      //     mainAxisAlignment: MainAxisAlignment.start,
      //     crossAxisAlignment: CrossAxisAlignment.start,
      //     children: <Widget>[
      //       SizedBox(
      //         height: 20.0,
      //       ),
      //       Row(
      //         children: [
      //           Padding(
      //             padding: const EdgeInsets.all(8.0),
      //             child: Icon(FontAwesomeIcons.speakap, size: 25),
      //           ),
      //           SizedBox(height: 50.0, width: 15.0),
      //           Text("Select Language:",
      //               style: TextStyle(
      //                 fontFamily: 'The',
      //                 fontWeight: FontWeight.bold,
      //                 fontSize: 22,
      //                 color: Colors.black,
      //               )),
      //         ],
      //       ),
      //       SizedBox(
      //         height: 20.0,
      //       ),
      //       Padding(
      //         padding: const EdgeInsets.all(2.0),
      //         child: Container(
      //           width: 275.0,
      //           child: Row(
      //             children: <Widget>[
      //               Radio(
      //                 value: 1,
      //                 groupValue: 1,
      //                 onChanged: (val) {
      //                   //setSelectedRadio(val);
      //                   context.locale = Locale('en', 'US');
      //                 },
      //               ),
      //               Text(
      //                 'English',
      //                 style: TextStyle(fontSize: 24),
      //               )
      //             ],
      //           ),
      //         ),
      //       ),
      //       Container(
      //         width: 150,
      //         child: Row(
      //           children: <Widget>[
      //             Radio(
      //               value: 1,
      //               groupValue: 0,
      //               onChanged: (val) {
      //                 //setSelectedRadio(val);
      //                 context.locale = Locale('hi', 'IN');
      //               },
      //             ),
      //             Text('Hindi', style: TextStyle(fontSize: 24))
      //           ],
      //         ),
      //       ),
      //       Container(
      //         width: 150,
      //         child: Row(
      //           children: <Widget>[
      //             Radio(
      //               value: 1,
      //               groupValue: 0,
      //               onChanged: (val) {
      //                 //setSelectedRadio(val);
      //                 context.locale = Locale('mr', 'IN');
      //               },
      //             ),
      //             Text('Marathi', style: TextStyle(fontSize: 24))
      //           ],
      //         ),
      //       )
      //     ],
      //   ),
      // )
    );
  }
}
