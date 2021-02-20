import 'package:flutter/material.dart';
import 'package:easy_localization/easy_localization.dart';

class MyAccount extends StatelessWidget {
  //static const routeName ='MyAccount';
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: EditProfilePage(),
    );
  }
}

class EditProfilePage extends StatefulWidget {
  @override
  _EditProfilePageState createState() => _EditProfilePageState();
}

class _EditProfilePageState extends State<EditProfilePage> {
  bool showPassword = false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Go Home',
        ).tr(),
      ),
      body: Container(
        padding: EdgeInsets.only(left: 16, top: 25, right: 16),
        child: GestureDetector(
          onTap: () {
            FocusScope.of(context).unfocus();
          },
          child: ListView(
            children: [
              Text(
                "Customer Profile",
                style: TextStyle(fontSize: 25, fontWeight: FontWeight.w500),
              ),
              SizedBox(
                height: 15,
              ),
              Center(
                child: Stack(
                  children: [
                    Container(
                      width: 130,
                      height: 130,
                      decoration: BoxDecoration(
                        border: Border.all(
                            width: 4,
                            color: Theme.of(context).scaffoldBackgroundColor),
                        boxShadow: [
                          BoxShadow(
                              spreadRadius: 2,
                              blurRadius: 10,
                              color: Colors.black.withOpacity(0.1),
                              offset: Offset(0, 10))
                        ],
                        shape: BoxShape.circle,
                        image: DecorationImage(
                          fit: BoxFit.cover,
                          image: AssetImage("assets/images/user.png"),
                        ),
                      ),
                    ),
                    Positioned(
                        bottom: 0,
                        right: 0,
                        child: Container(
                          height: 40,
                          width: 40,
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            border: Border.all(
                              width: 4,
                              color: Theme.of(context).scaffoldBackgroundColor,
                            ),
                            color: Colors.blue,
                          ),
                          child: Icon(
                            Icons.edit,
                            color: Colors.white,
                          ),
                        )),
                  ],
                ),
              ),
              SizedBox(
                height: 35,
              ),
              buildTextField("Full Name", "Alex Tod", false),
              buildTextField("E-mail", "abc@gmail.com", false),
              buildTextField("Password", "********", true),
              buildTextField("Location", "TLV, Israel", false),
              SizedBox(
                height: 35,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  OutlineButton(
                    padding: EdgeInsets.symmetric(horizontal: 50),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20)),
                    onPressed: () {},
                    child: Text("EDIT",
                        style: TextStyle(
                            fontSize: 14,
                            letterSpacing: 2.2,
                            color: Colors.black)),
                  ),
                  RaisedButton(
                    onPressed: () {},
                    color: Colors.blue,
                    padding: EdgeInsets.symmetric(horizontal: 50),
                    elevation: 2,
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20)),
                    child: Text(
                      "SAVE",
                      style: TextStyle(
                          fontSize: 14,
                          letterSpacing: 2.2,
                          color: Colors.white),
                    ),
                  )
                ],
              )
            ],
          ),
        ),
      ),
    );
  }

  Widget buildTextField(
      String labelText, String placeholder, bool isPasswordTextField) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 35.0),
      child: TextField(
        obscureText: isPasswordTextField ? showPassword : false,
        decoration: InputDecoration(
            suffixIcon: isPasswordTextField
                ? IconButton(
                    onPressed: () {
                      setState(() {
                        showPassword = !showPassword;
                      });
                    },
                    icon: Icon(
                      Icons.remove_red_eye,
                      color: Colors.blue,
                    ),
                  )
                : null,
            contentPadding: EdgeInsets.only(bottom: 3),
            labelText: labelText,
            floatingLabelBehavior: FloatingLabelBehavior.always,
            hintText: placeholder,
            hintStyle: TextStyle(
              fontSize: 16,
              fontWeight: FontWeight.bold,
              color: Colors.black,
            )),
      ),
    );
  }
}

// import 'package:flutter/material.dart';
// import 'package:easy_localization/easy_localization.dart';
// //import 'package:flutter/cupertino.dart';

// class MyAccount extends StatefulWidget {
//   @override
//   _MyAccountState createState() => _MyAccountState();
// }

// class _MyAccountState extends State<MyAccount> {
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('go_h').tr(),
//       ),
//       body: Column(
//           mainAxisAlignment: MainAxisAlignment.start,
//           crossAxisAlignment: CrossAxisAlignment.stretch,
//           children: <Widget>[
//             Container(
//               alignment: Alignment.center,
//               decoration: BoxDecoration(
//                 color: Colors.white,
//                 border: Border.all(color: Colors.white),
//                 boxShadow: [
//                   BoxShadow(
//                     color: Colors.grey,
//                     blurRadius: 10,
//                   )
//                 ],
//               ),
//               width: 400.0,
//               height: 50.0,
//               child: Text('my_a',
//                       style: TextStyle(
//                           fontSize: 21,
//                           color: Colors.blue,
//                           fontWeight: FontWeight.bold))
//                   .tr(),
//             ),
//             SizedBox(
//               height: 20.0,
//             ),

//             //  Container(
//             //   alignment: Alignment.center,
//             //   decoration: BoxDecoration(
//             //     color: Colors.white,
//             //     border: Border.all(color: Colors.white),
//             //   ),
//             //   width: 400.0,
//             //   height: 50.0,
//             //   child: Text('cal',
//             //           style: TextStyle(fontSize: 21, color: Colors.blue ,fontWeight: FontWeight.bold)
//             //           ).tr(),
//             //   ),
//             // SizedBox(
//             //   height: 20.0,
//             // ),

//             new Container(
//               color: Color(0xffFFFFFF),
//               child: Padding(
//                   padding: EdgeInsets.only(bottom: 25.0),
//                   child: new Column(
//                     crossAxisAlignment: CrossAxisAlignment.start,
//                     mainAxisAlignment: MainAxisAlignment.start,
//                     children: <Widget>[
//                       Padding(
//                           padding: EdgeInsets.only(
//                               left: 25.0, right: 25.0, top: 25.0),
//                           child: new Row(
//                             mainAxisAlignment: MainAxisAlignment.spaceBetween,
//                             mainAxisSize: MainAxisSize.max,
//                             children: <Widget>[
//                               new Column(
//                                 mainAxisAlignment: MainAxisAlignment.start,
//                                 mainAxisSize: MainAxisSize.min,
//                                 children: <Widget>[
//                                   new Text(
//                                     'Parsonal Information',
//                                     style: TextStyle(
//                                         fontSize: 18.0,
//                                         fontWeight: FontWeight.bold),
//                                   ),
//                                 ],
//                               ),
//                               new Column(
//                                 mainAxisAlignment: MainAxisAlignment.end,
//                                 mainAxisSize: MainAxisSize.min,
//                                 children: <Widget>[
//                                   // _status ? _getEditIcon() : new Container(),
//                                 ],
//                               )
//                             ],
//                           )),
//                       Padding(
//                           padding: EdgeInsets.only(
//                               left: 25.0, right: 25.0, top: 25.0),
//                           child: new Row(
//                             mainAxisSize: MainAxisSize.max,
//                             children: <Widget>[
//                               new Column(
//                                 mainAxisAlignment: MainAxisAlignment.start,
//                                 mainAxisSize: MainAxisSize.min,
//                                 children: <Widget>[
//                                   new Text(
//                                     'Name',
//                                     style: TextStyle(
//                                         fontSize: 16.0,
//                                         fontWeight: FontWeight.bold),
//                                   ),
//                                 ],
//                               ),
//                             ],
//                           )),
//                       Padding(
//                           padding: EdgeInsets.only(
//                               left: 25.0, right: 25.0, top: 2.0),
//                           child: new Row(
//                             mainAxisSize: MainAxisSize.max,
//                             children: <Widget>[
//                               new Flexible(
//                                 child: new TextField(
//                                   decoration: const InputDecoration(
//                                     hintText: "Enter Your Name",
//                                   ),
//                                 ),
//                               ),
//                             ],
//                           )),
//                       Padding(
//                           padding: EdgeInsets.only(
//                               left: 25.0, right: 25.0, top: 25.0),
//                           child: new Row(
//                             mainAxisSize: MainAxisSize.max,
//                             children: <Widget>[
//                               new Column(
//                                 mainAxisAlignment: MainAxisAlignment.start,
//                                 mainAxisSize: MainAxisSize.min,
//                                 children: <Widget>[
//                                   new Text(
//                                     'Mobile',
//                                     style: TextStyle(
//                                         fontSize: 16.0,
//                                         fontWeight: FontWeight.bold),
//                                   ),
//                                 ],
//                               ),
//                             ],
//                           )),
//                       Padding(
//                         padding:
//                             EdgeInsets.only(left: 25.0, right: 25.0, top: 2.0),
//                         child: new Row(
//                           mainAxisSize: MainAxisSize.max,
//                           children: <Widget>[
//                             new Flexible(
//                               child: new TextField(
//                                 decoration: const InputDecoration(
//                                     hintText: "Enter Mobile Number"),
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),

//                       Padding(
//                           padding: EdgeInsets.only(
//                               left: 25.0, right: 25.0, top: 25.0),
//                           child: new Row(
//                             mainAxisSize: MainAxisSize.max,
//                             children: <Widget>[
//                               new Column(
//                                 mainAxisAlignment: MainAxisAlignment.start,
//                                 mainAxisSize: MainAxisSize.min,
//                                 children: <Widget>[
//                                   new Text(
//                                     'Address',
//                                     style: TextStyle(
//                                         fontSize: 16.0,
//                                         fontWeight: FontWeight.bold),
//                                   ),
//                                 ],
//                               ),
//                             ],
//                           )),
//                       Padding(
//                         padding:
//                             EdgeInsets.only(left: 25.0, right: 25.0, top: 2.0),
//                         child: new Row(
//                           mainAxisSize: MainAxisSize.max,
//                           children: <Widget>[
//                             new Flexible(
//                               child: new TextField(
//                                 decoration: const InputDecoration(
//                                     hintText: "Enter Address"),
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),
//                     SizedBox(
//                       height: 15.0,
//                     ),
//                     Row(
//                       children: [
//                         Padding(
//                           padding:
//                           EdgeInsets.only(left: 50.0, right: 25.0, top: 2.0),
//                           child: RaisedButton(
//                             onPressed: () {
//                               // Navigator.push(
//                               //     context,
//                               //     MaterialPageRoute(
//                               //         builder: (context) => JarEntry()));
//                             },
//                             child: Text(
//                               'Edit',
//                               textAlign: TextAlign.center,
//                               style: TextStyle(
//                                   fontSize: 16,
//                                   color: Colors.blue,
//                                   fontWeight: FontWeight.bold),
//                             ),
//                             //.tr(),
//                             color: Colors.white,
//                             shape: RoundedRectangleBorder(
//                                 borderRadius: BorderRadius.circular(10.0),
//                                 side: BorderSide(color: Colors.white)),
//                           ),
//                         ),
//                         SizedBox(
//                           width: 10.0,
//                         ),
//                         Padding(
//                           padding:
//                           EdgeInsets.only(left: 20.0, right: 25.0, top: 2.0),
//                           child: RaisedButton(
//                             onPressed: () {
//                               // Navigator.push(
//                               //     context,
//                               //     MaterialPageRoute(
//                               //         builder: (context) => JarEntry()));
//                             },
//                             child: Text(
//                               'Submit',
//                               textAlign: TextAlign.center,
//                               style: TextStyle(
//                                   fontSize: 16,
//                                   color: Colors.blue,
//                                   fontWeight: FontWeight.bold),
//                             ),
//                             //.tr(),
//                             color: Colors.white,
//                             shape: RoundedRectangleBorder(
//                                 borderRadius: BorderRadius.circular(10.0),
//                                 side: BorderSide(color: Colors.white)),
//                           ),
//                         ),
//                       ],
//                     ),

//                     ],
//                   )),
//             )
//           ]),
//     );
//   }
// }
