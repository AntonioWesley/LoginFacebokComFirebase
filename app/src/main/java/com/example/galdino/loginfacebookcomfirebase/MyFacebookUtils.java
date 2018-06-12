package com.example.galdino.loginfacebookcomfirebase;

import android.os.Bundle;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nemes on 28/04/2018.
 */

public class MyFacebookUtils
{
    private static final String READ_PERMISSION_PUBLIC_PROFILE = "public_profile";
    private static final String READ_PERMISSION_EMAIL = "email";
    private static final String READ_PERMISSION_USER_BIRTHDAY = "user_birthday";
    private static final String READ_PERMISSION_USER_GENDER = "user_gender";
    private static final String READ_PERMISSION_USER_LOCATION = "user_location";

    private static final String FIELDS = "fields";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_GENDER = "gender";
    private static final String FIELD_BIRTHDAY = "birthday";
    private static final String FIELD_LOCATION = "location";

    public void graphRequest(AccessToken accessToken, GraphRequest.GraphJSONObjectCallback graphJSONObjectCallback)
    {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                graphJSONObjectCallback);
        Bundle parameters = new Bundle();
        parameters.putString(FIELDS, generateParametersToSend());
        request.setParameters(parameters);
        request.executeAsync();
    }

    private String generateParametersToSend()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FIELD_ID);
        stringBuilder.append(",");
        stringBuilder.append(FIELD_NAME);
        stringBuilder.append(",");
        stringBuilder.append(FIELD_EMAIL);
        stringBuilder.append(",");
        stringBuilder.append(FIELD_GENDER);
        stringBuilder.append(",");
        stringBuilder.append(FIELD_BIRTHDAY);
        stringBuilder.append(",");
        stringBuilder.append(FIELD_LOCATION);
        return stringBuilder.toString();
    }

    public Pessoa getPeopleObject(JSONObject object)
    {
        Pessoa pessoa = new Pessoa();

        String facebookId = getField(object, FIELD_ID);
//        pessoa.setSenha(facebookId);
//        String name = getField(object, FIELD_NAME);
//        pessoa.setDsNome(name);
//        String email = getField(object, FIELD_EMAIL);
//        pessoa.setEmail(email);
        String gender = getField(object, FIELD_GENDER);
        pessoa.setMasculino(isMale(gender));
        String birthday = getField(object, FIELD_BIRTHDAY); // 01/31/1980 format
        pessoa.setSdtNascimento(birthday);

        return pessoa;
    }

    private String getField(JSONObject object, String field)
    {
        try {
            return object.getString(field);
        } catch (JSONException e) {
            return "";
        }
    }

    private boolean isMale(String gender) {
        return TextUtils.isEmpty(gender) || gender.equals("male");
    }


    public List<String> getReadPermissions() {
        return Arrays.asList(
                READ_PERMISSION_PUBLIC_PROFILE,READ_PERMISSION_EMAIL, READ_PERMISSION_USER_BIRTHDAY, READ_PERMISSION_USER_GENDER, READ_PERMISSION_USER_LOCATION);
    }
}
