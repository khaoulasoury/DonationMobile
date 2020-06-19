/*
 * Copyright (c) 2018, Codename One and/or its affiliates. All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package Gui;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.util.CallbackAdapter;

/**
 * Simple API for sending an email via sendgrid
 *
 * @author Shai Almog
 */
public class SendGrid {
    private String token;
    private SendGrid(String token) {
        this.token = token;
    }
    
    /**
     * You need the API token from send grid to use this class, it's available 
     * by signing up to their service
     * @param token the API token
     * @return the class instance
     */
    public static SendGrid create(String token) {
        return new SendGrid(token);
    }

    /**
     * Sends an email synchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email
     */
    public void sendSync(String to, String from, String subject, String body) {
        Response<String> s = Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/plain\", \"value\": \"" + 
                body + "\"}]}").getAsString();        
    }

    /**
     * Sends an email synchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email in html format
     */
    public void sendHtmlSync(String to, String from, String subject, String body) {
        Response<String> s = Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/html\", \"value\": \"" + 
                body + "\"}]}").getAsString();        
    }

    /**
     * Sends an email asynchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email
     */
    public void sendASync(String to, String from, String subject, String body) {
        Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/plain\", \"value\": \"" + 
                body + "\"}]}").getAsStringAsync(new CallbackAdapter<>());
    }

     /**
     * Sends an email asynchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email in html format
     */
    public void sendHtmlASync(String to, String from, String subject, String body) {
        Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/html\", \"value\": \"" + 
                body + "\"}]}").getAsStringAsync(new CallbackAdapter<>());
    }
   
}
