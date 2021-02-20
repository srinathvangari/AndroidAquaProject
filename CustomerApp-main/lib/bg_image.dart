import 'package:flutter/material.dart';

// ignore: camel_case_types
class bg_image  extends StatelessWidget {
   @override
  Widget build(BuildContext context) {
    return Image.asset(
      "assets/images/login.png",
      fit: BoxFit.cover,
      color: Colors.black.withOpacity(0.5),
      colorBlendMode: BlendMode.darken,
    );
  }
}

