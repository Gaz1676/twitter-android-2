package app.mytweetapp.helpers;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

/**
 * ContactHelper is used for getting a contact,
 *                             displaying a contact
 *                                & sending emails to a contact
 * The Helper is used to reduce code (DRY) where necessary
 */

// helper used with the getting, displaying & sending email to contact
public class ContactHelper {


    public static String getDisplayName(Context context, Intent data)
    {
        String contact = "unable to find contact";
        Uri contactUri = data.getData();
        String[] queryFields = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
        Cursor c = context.getContentResolver().query(contactUri, queryFields, null, null, null);
        if (c.getCount() == 0)
        {
            c.close();
            return contact;
        }
        c.moveToFirst();
        contact = c.getString(0);
        c.close();

        return contact;
    }


    public static String getEmail(Context context, Intent data) {
        String email = "no email";

        Uri contactUri = data.getData();
        ContentResolver cr = context.getContentResolver();

        Cursor cur = cr.query(contactUri, null, null, null, null);

        if (cur.getCount() > 0) {
            try {
                cur.moveToFirst();
                String contactId = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);
                emails.moveToFirst();
                email = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                emails.close();
            } catch (Exception e) {
                Toast.makeText(context, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return email;
    }


    public static String getContact(Context context, Intent data)
    {
        String contact = "unable to find contact";
        Uri contactUri = data.getData();
        String[] queryFields = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
        Cursor c = context.getContentResolver().query(contactUri, queryFields, null, null, null);
        if (c.getCount() == 0)
        {
            c.close();
            return contact;
        }
        c.moveToFirst();
        contact = c.getString(0);
        c.close();

        return contact;
    }


    public static void sendEmail(Context context, String email, String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(emailIntent, "Sending Email"));
    }
}