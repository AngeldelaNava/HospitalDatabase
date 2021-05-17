<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Hospital Patient-Disease Report</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
      <th>Name</th>
      <th>BloodType</th>
      <th>Gender</th>      
    </tr>
    <xsl:for-each select="Patient">
    <xsl:if test="roomNumber>1">
      <tr>
        <td><xsl:value-of select="name"/></td>
        <td><xsl:value-of select="bloodType"/></td>
        <td><xsl:value-of select="gender"/></td>        
      </tr>
    </xsl:if>
    </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>