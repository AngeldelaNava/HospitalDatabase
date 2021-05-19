<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
  <html>
  <body>
    <h2>Patient's medical history</h2>
    <p><b>Name: </b><xsl:value-of select="//name" /></p>
    <p><b>Id: </b><xsl:value-of select="// @id" /></p>
    <table border="1">
      <tr bgcolor="#9acd32">
      <th>Name</th>
      <th>Prescription</th>    
    </tr>
    <xsl:for-each select="Patient/PatientDisease/Disease">
    <xsl:if test="prescription='antibiotics'">
      <tr>
        <td><xsl:value-of select="@name"/></td>
        <td><xsl:value-of select="prescription"/></td>
      </tr>
    </xsl:if>
    </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>