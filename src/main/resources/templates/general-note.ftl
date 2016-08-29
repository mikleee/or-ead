<#-- @ftlvariable name="virtual1ContactName" type="java.lang.String" -->
<#-- @ftlvariable name="virtual1ContactPhone" type="java.lang.String" -->
<#-- @ftlvariable name="popPort" type="java.lang.String" -->
<#-- @ftlvariable name="exchange" type="java.lang.String" -->
<#-- @ftlvariable name="floor" type="java.lang.String" -->
<#-- @ftlvariable name="rack" type="java.lang.String" -->
<#-- @ftlvariable name="room" type="java.lang.String" -->
<#-- @ftlvariable name="contactName" type="java.lang.String" -->
<#-- @ftlvariable name="contactPhone" type="java.lang.String" -->

**Project ref: QBR781 Virtual 1 Router install required.
(Appendix A lists the various Juniper routers and EDD (Ethernet Demarcation Device types with diagrams for each)
Type: Customer owned equipment at customer’s site.
Connect patch cable ${popPort} which will be labelled in the exchange on ${exchange}
<#if floor??>, ${floor}</#if>
<#if room??>, Suite ${room}</#if>
<#if rack??>, Rack ${rack}</#if>.
IMPORTANT: When installing a new MUX please ensure that the fibre box is installed at the bottom of the rack U1 to U4.
16port MUXs should be installed starting at U5 and moving up the rack Call into V1 Provisioning on  ${virtual1ContactPhone} once install has been completed.
Commercial contact: Order Validation Team and ${virtual1ContactPhone} option 3.
Emergency contact: Site contact & number - <#if contactName??>${contactName}</#if><#if contactName??> ${contactPhone}</#if> (Site Contact only to be used in emergency.
All communication needs to be managed via Virtual1). Primary Site Contact A/B End: ${virtual1ContactName} / ${virtual1ContactPhone}.