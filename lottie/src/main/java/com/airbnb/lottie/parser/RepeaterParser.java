package com.airbnb.lottie.parser;

import android.util.JsonReader;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.Repeater;

import java.io.IOException;

public class RepeaterParser {

  private RepeaterParser() {}

  public static Repeater parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    String name = null;
    AnimatableFloatValue copies = null;
    AnimatableFloatValue offset = null;
    AnimatableTransform transform = null;

    while (reader.hasNext()) {
      switch (reader.nextName()) {
        case "nm":
          name = reader.nextString();
          break;
        case "c":
          copies = AnimatableFloatValue.Factory.newInstance(reader, composition, false);
          break;
        case "o":
          offset = AnimatableFloatValue.Factory.newInstance(reader, composition, false);
          break;
        case "tr":
          transform = AnimatableTransform.Factory.newInstance(reader, composition);
          break;
        default:
          reader.skipValue();
      }
    }

    return new Repeater(name, copies, offset, transform);
  }
}