package pl.put.poznan.building;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.building.app.CustomCardLayout;
import pl.put.poznan.building.app.MenuPanel;
import javax.swing.*;
import java.awt.event.ActionListener;
import static org.mockito.Mockito.*;
public class TestMenuPanel {

    private MenuPanel menuPanelUnderTest;

    @BeforeEach
    void setUp() {

        menuPanelUnderTest = new MenuPanel();
        menuPanelUnderTest.serverOutputButton = mock(JButton.class);
        menuPanelUnderTest.addLocation = mock(JButton.class);
        menuPanelUnderTest.getInfo = mock(JButton.class);
        menuPanelUnderTest.goBuildings = mock(JButton.class);
        menuPanelUnderTest.getCost = mock(JButton.class);
        menuPanelUnderTest.powerCost = mock(JLabel.class);
    }

    @Test
    void testAddServerOutputButtonActionListener() {

        final ActionListener mockActionListener = mock(ActionListener.class);

        menuPanelUnderTest.addServerOutputButtonActionListener(mockActionListener);

        verify(menuPanelUnderTest.serverOutputButton).addActionListener(any(ActionListener.class));

    }

    @Test
    void testAddAddLocationActionListener() {

        final ActionListener mockActionListener = mock(ActionListener.class);

        menuPanelUnderTest.addAddLocationActionListener(mockActionListener);

        verify(menuPanelUnderTest.addLocation).addActionListener(any(ActionListener.class));
    }

    @Test
    void testAddGetInfoActionListener() {
        final ActionListener mockActionListener = mock(ActionListener.class);

        menuPanelUnderTest.addGetInfoActionListener(mockActionListener);

        verify(menuPanelUnderTest.getInfo).addActionListener(any(ActionListener.class));
    }

    @Test
    void testAddGoBuildingsActionListener() {

        ActionListener mockActionListener = mock(ActionListener.class);

        menuPanelUnderTest.addGoBuildingsActionListener(mockActionListener);

        verify(menuPanelUnderTest.goBuildings).addActionListener(any(ActionListener.class));
    }

    @Test
    void testAddGetPowerCost() {

        ActionListener mockActionListener = mock(ActionListener.class);

        menuPanelUnderTest.addGetPowerCost(mockActionListener);

        verify(menuPanelUnderTest.getCost).addActionListener(any(ActionListener.class));
    }
}