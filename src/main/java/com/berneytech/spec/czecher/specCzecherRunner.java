/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;


import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author bernelia000
 */
public class specCzecherRunner {
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Oshi (https://github.com/oshi/oshi)
 *
 * Copyright (c) 2010 - 2018 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Maintainers:
 * dblock[at]dblock[dot]org
 * widdis[at]gmail[dot]com
 * enrico.bianchi[at]gmail[dot]com
 *
 * Contributors:
 * https://github.com/oshi/oshi/graphs/contributors
 */


/**
 * The Class SystemInfoTest.
 *
 * @author dblock[at]dblock[dot]org
 */

    /**
     * Test system info.
     */
    //@Test
    //public void testCentralProcessor() {
    //    assertFalse(PlatformEnum.UNKNOWN.equals(SystemInfo.getCurrentPlatformEnum()));
    //}
                                
    /**
     * The main method, demonstrating use of classes.
     *
     * @param args
     *            the arguments
     * @throws java.io.IOException
     * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        SpecInfo a=new SpecInfo();
        List<List<String>> specs = SpecInfo.run();
        for(List s : specs) {
            for ( Object str : s){
                out.println((String) str);
            }
        ExcelWriter b= new ExcelWriter(specs);
        b.createSheet();
            
}
       
    }
}
    
