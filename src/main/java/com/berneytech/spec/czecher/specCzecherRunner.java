package com.berneytech.spec.czecher;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.IOException;
import static java.lang.System.out;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
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
         * @param args
         * @throws java.io.IOException
         * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
    */
    /*
    List<String> lines = Arrays.asList("The first line", "The second line");
        for(int x=0;;x++){
        Path file = Paths.get("H:\\Trash\\garbage"+x+".txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
    */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        run();
    }
    public static void run() throws IOException, InvalidFormatException{
        List<List<String>> specs = SpecInfo.run();
        for (List<String> d: TempInfo.run()){
            specs.add(d);
        }
        new ExcelWriter(specs){{createSheet();}};
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login();
            }
        });
        }
    public static void login(){
        final JFrame frame = new JFrame("JDialog Demo");
        LoginDialog loginDlg = new LoginDialog(frame,"poi-generated-file.xlsx");
        loginDlg.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.dispose();
        }
    }   
    

    
