package uk.co.virtual1.salesforce.object;


/**
 * crated by m.tkachenko on 29.02.16 21:01
 */
public class SfAttachment extends ChildObject {
    private static final long serialVersionUID = 1L;

    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

}
