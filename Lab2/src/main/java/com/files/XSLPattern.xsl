<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/students">
        <html>
            <body>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ПІБ</th>
                            <th>Факультет</th>
                            <th>Кафедра</th>
                            <th>Дисципліни</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="student">
                            <tr>
                                <td>
                                    <xsl:value-of select="fullname"/>
                                </td>
                                <td>
                                    <xsl:value-of select="faculty"/>
                                </td>
                                <td>
                                    <xsl:value-of select="cathedra"/>
                                </td>
                                <td>
                                    <xsl:value-of select="disciplines"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>