/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Sandeep Somavarapu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ibm.team.rtc.extensions.workitem.copy.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.ibm.team.repository.common.TeamRepositoryException;
import com.ibm.team.workitem.common.model.IAttribute;

public class TargetAttributes {

	private final RepositoryContext fContext;

	private Map<String, IAttribute> fAttributes;

	public TargetAttributes(EvaluationContext evaluationContext) {
		fContext= evaluationContext.targetContext;
	}

	public IAttribute findAttribute(String id, IProgressMonitor monitor) throws TeamRepositoryException {
		return getAttributes(monitor).get(id);
	}

	private Map<String, IAttribute> getAttributes(IProgressMonitor monitor) throws TeamRepositoryException {
		if (fAttributes == null) {
			fAttributes= new HashMap<String, IAttribute>();
			for (IAttribute attribute : fContext.workItemClient.findAttributes(fContext.projectArea, monitor)) {
				fAttributes.put(attribute.getIdentifier(), attribute);
			}
		}
		return fAttributes;
	}

}
