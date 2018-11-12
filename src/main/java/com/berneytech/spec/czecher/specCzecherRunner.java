/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;

import java.awt.Desktop;
import java.io.File;

import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author liamrberney
 */
public class specCzecherRunner {
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
    public static void main(String[] args) throws IOException, InvalidFormatException {
        run();
    }
    public static void run() throws IOException, InvalidFormatException{
        SpecInfo a=new SpecInfo();
        List<List<String>> specs = SpecInfo.run();
        TempInfo c = new TempInfo();
        List<List<String>> sensors = TempInfo.run();
        for (List<String> d: sensors){
            specs.add(d);
        }
        ExcelWriter b= new ExcelWriter(specs);
        b.createSheet();
        
        FileUploader.upload("poi-generated-file.xlsx");
    }
}   
    

    
