package de.mth.game.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Writer {

	public Writer() throws UnsupportedEncodingException, FileNotFoundException,
			IOException {
		// try (OutputStreamWriter writer = new OutputStreamWriter(
		// new FileOutputStream("Output.json"), "UTF-8")) {
		// Gson gson = new GsonBuilder().create();
		// gson.toJson("Hello", writer);
		// gson.toJson(123, writer);
		// }

		test();

		test2();
		System.out.println("Writer.Writer()");
	}

	public void test3() {

	}

	public void test() {
		String json = "{\"brand\" : \"Toyota\", \"doors\" : 5}";

		// JsonReader jsonReader = new JsonReader(new StringReader(json));

	}

	public void test2() {
		JsonParser parser = new JsonParser();

		String json = "{ \"f1\":\"Hello\",\"f2\":{\"f3\":\"World\"}}";

		JsonElement jsonTree = parser.parse(json);

		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();

			JsonElement f1 = jsonObject.get("f1");

			JsonElement f2 = jsonObject.get("f2");

			if (f2.isJsonObject()) {
				JsonObject f2Obj = f2.getAsJsonObject();

				JsonElement f3 = f2Obj.get("f3");
				System.out.println("Writer.test2() " + f3);
			}

		}

	}
}
