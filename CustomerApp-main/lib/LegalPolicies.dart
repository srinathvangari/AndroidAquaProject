//import 'package:customerapp/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:easy_localization/easy_localization.dart';


class LegalPolicies extends StatelessWidget {
 @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(
            'Go Home',
          ).tr(),
        ),
        body: SafeArea(
            child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
              
              Container(
                alignment: Alignment.center,
                decoration: BoxDecoration(
                  color: Colors.white,
                  border: Border.all(color: Colors.white),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey,
                      blurRadius: 10,
                    )
                  ],
                ),
                width: 400.0,
                height: 50.0,
                child: Text('LEGAL POLICIES',
                        style: TextStyle(
                            fontSize: 15,
                            color: Colors.black,
                            fontWeight: FontWeight.bold))
                    .tr(),
              ),
              SizedBox(
                height: 20.0,
              ),

              // Container(
              //   alignment: Alignment.center,
              //   decoration: BoxDecoration(
              //     color: Colors.white,
              //     border: Border.all(color: Colors.white),
              //   ),
              //   width: 400.0,
              //   height: 50.0,
              //   child: Text('cal',
              //           style: TextStyle(
              //               fontSize: 21,
              //               color: Colors.blue,
              //               fontWeight: FontWeight.bold))
              //       .tr(),
              // ),
              // SizedBox(
              //   height: 20.0,
              // ),

            
            ]
            ),
            ),

        );
  }
}
