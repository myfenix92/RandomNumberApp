package com.hfad.randomnumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateRandomNumber {

    private int m_fromNumber;
    private int m_toNumber;
    private int m_countNumber;
    private boolean m_isUnique;
    private List<Integer> m_numbers = new ArrayList<>();

    public void setRange(String fromNumber, String toNumber, int countNumber) {
        m_fromNumber = Integer.parseInt(fromNumber);
        m_toNumber = Integer.parseInt(toNumber);
        m_countNumber = countNumber;
        m_numbers.clear();
    }

    public List<Integer> setSortBy(int sort, List<Integer> values) {
        List<Integer> numbers = values;
        switch (sort) {
            case (0):
            {
                break;
            }
            case (1):
            {
                Collections.sort(numbers);
                break;
            }
            case (2):
            {
                Collections.sort(numbers, Collections.reverseOrder());
                break;
            }
        }
        return numbers;
    }

    public int getValueRange(boolean uniqueValue) {
        m_isUnique = uniqueValue;
        if (m_isUnique) {
            if (m_toNumber - m_fromNumber + 1 < m_countNumber) {
                m_countNumber = m_toNumber - m_fromNumber + 1;
            }
        }
        return m_countNumber;
    }

    public List<Integer> getRandomNumbers() {
        int outputValue;
        while (m_numbers.size() < m_countNumber) {
            outputValue = (int) ((Math.random() * m_toNumber - m_fromNumber) + 1) + m_fromNumber;

            if (m_isUnique) {
                if (!m_numbers.contains(outputValue)) {
                    m_numbers.add(outputValue);
                }
            }
            else {
                m_numbers.add(outputValue);
            }
        }
        return m_numbers;
    }

}
