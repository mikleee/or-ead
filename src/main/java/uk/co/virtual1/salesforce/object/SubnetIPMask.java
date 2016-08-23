package uk.co.virtual1.salesforce.object;

public enum SubnetIPMask {

    MASK_32("/32", "255.255.255.255"),
    MASK_31("/31", "255.255.255.254"),
    MASK_30("/30", "255.255.255.252"),
    MASK_29("/29", "255.255.255.248"),
    MASK_28("/28", "255.255.255.240"),
    MASK_27("/27", "255.255.255.224"),
    MASK_26("/26", "255.255.255.192"),
    MASK_25("/25", "255.255.255.128"),
    MASK_24("/24", "255.255.255.0");

    SubnetIPMask(String prefix, String mask) {
        this.prefix = prefix;
        this.mask = mask;
    }

    private String prefix;
    private String mask;

    public static String getMask(String ip) {
        if (ip != null && ip.contains("/")) {
            String[] spittedParts = ip.split("/");

            if (spittedParts.length > 1) {
                String prefix = "/" + spittedParts[1];
                for (SubnetIPMask mask : SubnetIPMask.values()) {
                    if (mask.prefix.equals(prefix)) {
                        return mask.mask;
                    }
                }
            }
        }
        return null;
    }

}